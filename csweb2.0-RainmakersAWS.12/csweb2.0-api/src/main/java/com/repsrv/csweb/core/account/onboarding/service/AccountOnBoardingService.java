package com.repsrv.csweb.core.account.onboarding.service;

import static org.apache.commons.collections.CollectionUtils.size;

import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.apache.commons.collections4.ListUtils;
import org.apache.ibatis.exceptions.PersistenceException;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.common.collect.LinkedHashMultimap;
import com.google.common.collect.LinkedListMultimap;
import com.google.common.collect.Multimap;
import com.google.common.collect.SetMultimap;
import com.google.gson.Gson;
import com.poiji.bind.Poiji;
import com.poiji.option.PoijiOptions;
import com.repsrv.csweb.core.account.imports.dao.AccountImportDao;
import com.repsrv.csweb.core.account.imports.service.AccountImportService;
import com.repsrv.csweb.core.account.imports.service.ExcellSheetsConstants;
import com.repsrv.csweb.core.account.imports.validators.TemplateValidator;
import com.repsrv.csweb.core.account.onboarding.dao.AccountOnBoardingDao;
import com.repsrv.csweb.core.account.onboarding.dao.AobRequestDriverReq;
import com.repsrv.csweb.core.account.onboarding.validators.AobTemplateValidator;
import com.repsrv.csweb.core.model.account.imports.ImportValidationResult;
import com.repsrv.csweb.core.model.account.imports.Row;
import com.repsrv.csweb.core.model.account.imports.RowError;
import com.repsrv.csweb.core.model.account.imports.TemplateImportResult;
import com.repsrv.csweb.core.model.account.imports.TemplateImportResult.TemplateImportResultBuilder;
import com.repsrv.csweb.core.model.account.onboarding.AobAccountInformation;
import com.repsrv.csweb.core.model.account.onboarding.AobContainerInformation;
import com.repsrv.csweb.core.model.account.onboarding.AobCreateProjectRequest;
import com.repsrv.csweb.core.model.account.onboarding.AobHandoff;
import com.repsrv.csweb.core.model.account.onboarding.AobProjectCreationDTO;
import com.repsrv.csweb.core.model.account.onboarding.AobRateInformation;
import com.repsrv.csweb.core.model.account.onboarding.AobSiteInformation;
import com.repsrv.csweb.core.model.account.onboarding.AobUploadHistoryRequest;
import com.repsrv.csweb.core.model.json.CSWebAction;
import com.repsrv.csweb.core.model.sp.StoredProcCallResult;
import com.repsrv.csweb.core.support.exception.SecurityUtils;
import com.repsrv.csweb.rest.exception.AccountImportSetupException;
import com.repsrv.csweb.rest.utils.CswebSecurityUtils;

@Service
public class AccountOnBoardingService extends AccountImportService<AobAccountInformation>
		implements ExcellSheetsConstants {

	private static final Logger logger = LoggerFactory.getLogger(AccountOnBoardingService.class);
	private static final int MAX_ACCOUNTS_LIMIT = 200;
	private static final String UPLOAD_FILENAME_FORMAT = "%s_%s_%s"; // {filename #}_{uploadTyoe}_{projectName}}
	private static final int BATCH_SIZE = 5500;
	private static final String TEMPLATE_KEY = "1735247690";
	private Map<String,String> dataSize = new HashMap<>();

	@Autowired
	private AccountOnBoardingDao accountOnBoardingDao;

	@Autowired
	private AccountImportDao accountImportDao;

	private static Gson gson = null;

	static {
		gson = AobTemplateValidator.gsonCreate();
	}

	private static final PoijiOptions options = AobTemplateValidator.poijiOptions();

	private ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
	private Validator validator = factory.getValidator();

	@Override
	protected ImportValidationResult serialize(Workbook workbook) {

		Sheet accountSheet = workbook.getSheet(ACCOUNT_SHEET_NAME);
		Sheet sitesSheet = workbook.getSheet(SITE_SHEET_NAME);
		Sheet containerSheet = workbook.getSheet(CONTAINER_SHEET_NAME);
		Sheet ratesSheet = workbook.getSheet(RATE_SHEET_NAME);

		List<AobAccountInformation> accounts = Poiji.fromExcel(accountSheet, AobAccountInformation.class, options);
		List<AobSiteInformation> sites = Poiji.fromExcel(sitesSheet, AobSiteInformation.class, options);
		List<AobContainerInformation> containers = Poiji.fromExcel(containerSheet, AobContainerInformation.class,
				options);
		List<AobRateInformation> rates = Poiji.fromExcel(ratesSheet, AobRateInformation.class, options);

		dataSize.put("accounts", size(accounts)+"");
		dataSize.put("sites", size(sites)+"");
		dataSize.put("containers", size(containers)+"");
		dataSize.put("rates", size(rates)+"");

		logger.debug("Found {} account rows", size(accounts));
		logger.debug("Found {} site rows", size(sites));
		logger.debug("Found {} container rows", size(containers));
		logger.debug("Found {} rates rows", size(rates));

		TemplateImportResultBuilder<AobAccountInformation> resultBuilder = convertToRelational(accounts, sites, containers, rates);

		return resultBuilder.build();
	}

	@Override
	protected void validate(ImportValidationResult result) {
		TemplateImportResult<AobAccountInformation> resiResult = (TemplateImportResult) result;
		// change to sorted set multimap for ordering
		SetMultimap<String, RowError> tabViolations = LinkedHashMultimap.create();

		resiResult.getAccounts().forEach(a -> {

			Set<ConstraintViolation<AobAccountInformation>> constraintViolations = validator.validate(a);

			constraintViolations.forEach(v -> {
				Row row = (Row) v.getLeafBean();
				tabViolations.put(row.getTabName(), row.addViolation(v));
			});
		});

		// check for duplicates

		Map<String, Collection<RowError>> duplicateKeyErrors = TemplateValidator.validateForDupes(resiResult);
		for (Map.Entry<String, Collection<RowError>> entry : duplicateKeyErrors.entrySet()) {
			Collection<RowError> cols = tabViolations.asMap().get(entry.getKey());
			if (cols == null && entry.getValue() != null)
				tabViolations.putAll(entry.getKey(), entry.getValue());
			else
				cols.addAll(entry.getValue());
		}

		Map<String, Collection<RowError>> origViolationMap = tabViolations.asMap();
		Map<String, Collection<RowError>> orderedViolations = new LinkedHashMap<>();
		Map<String, Collection<RowError>> orderedMappingViolations = new LinkedHashMap<>();
		for (String tabName : tabOrder) {
			if (origViolationMap.containsKey(tabName))
				orderedViolations.put(tabName, TemplateValidator.sort(origViolationMap.get(tabName)));

			if (resiResult.getUnmappedRows() != null && resiResult.getUnmappedRows().containsKey(tabName)) {
				orderedMappingViolations.put(tabName,
						TemplateValidator.sort(resiResult.getUnmappedRows().get(tabName)));
			}
		}
		resiResult.setValidationErrors(orderedViolations);
		resiResult.setUnmappedRows(orderedMappingViolations);
	}

	@Override
	public void persistData(
		 	List<?> accounts2,
			String fileName,
			String changeNumber,
			String uploadType,
			String aquisition,
			String projectId) throws AccountImportSetupException {
		List<AobAccountInformation> accounts = (List<AobAccountInformation>) accounts2;
		String fileNameFinal = String.format(UPLOAD_FILENAME_FORMAT, uploadType, aquisition, fileName);
		String user = CswebSecurityUtils.getLoggedInUser();

		AobProjectCreationDTO aobProjectCreationDTO = new AobProjectCreationDTO(aquisition, fileNameFinal, dataSize.get("accounts"), dataSize.get("sites"), dataSize.get("containers"), dataSize.get("rates"));
		AobCreateProjectRequest aobCreateProjectRequest = new AobCreateProjectRequest(CSWebAction.INSERT, user, aobProjectCreationDTO);
		accountOnBoardingDao.aobProjectCreation(aobCreateProjectRequest);

		if (Objects.isNull(aobCreateProjectRequest.getProjectId())) {
			throw new AccountImportSetupException("Error creating project ID. " + aobCreateProjectRequest.getReturnErrorMsg());
		}
		logger.info("PROJECT ID:  "+aobCreateProjectRequest.getProjectId());

				//this needs to be created for this request, not passed in
				projectId = aobCreateProjectRequest.getProjectId().trim();

				// extract all objects
				List<AobAccountInformation> accts = accounts;
				List<AobSiteInformation> sites = accts.stream().flatMap(a -> a.getAobSites().stream())
						.collect(Collectors.toList());
		
				List<AobContainerInformation> containers = sites.stream().flatMap(s -> s.getAobContainers().stream())
						.collect(Collectors.toList());
		
				List<AobRateInformation> rates = containers.stream().flatMap(c -> c.getAobRates().stream())
						.collect(Collectors.toList());
		
		
				// Create batches
				List<List<AobAccountInformation>> acctBatches = ListUtils.partition(accts, BATCH_SIZE);
				List<List<AobSiteInformation>> siteBatches = ListUtils.partition(sites, BATCH_SIZE);
				List<List<AobContainerInformation>> containerBatches = ListUtils.partition(containers, BATCH_SIZE);
				List<List<AobRateInformation>> rateBatches = ListUtils.partition(rates, BATCH_SIZE);
				invokeSave(projectId, "account", acctBatches);
				invokeSave(projectId, "site", siteBatches);
				invokeSave(projectId, "container", containerBatches);
				invokeSave(projectId, "rate", rateBatches);

				AobRequestDriverReq aobRequestDriverReq = new AobRequestDriverReq(user, projectId);
				logger.info("DRIVER REQUEST: "+aobRequestDriverReq.getJson());
				accountOnBoardingDao.aobRequestDriver(aobRequestDriverReq);
				logger.info("RESPUESTA: "+aobRequestDriverReq.getReturnErrorMsg());
				logger.info("RESPUESTA2: "+aobRequestDriverReq.getReturnStatus());
		}

	private void checkMissingAccounts(List<AobAccountInformation> accounts, List<? extends Row> items, Function<Object, String> getAccountNumber, String sheetName, Multimap<String, RowError> unmappedSheetRows) {
	    Set<String> accountNumbers = accounts.stream().map(AobAccountInformation::getAccountNumber).collect(Collectors.toSet());
	    Set<String> itemAccountNumbers = items.stream().map(getAccountNumber).collect(Collectors.toSet());
	    accountNumbers.removeAll(itemAccountNumbers);
	    String cleanSheet = sheetName.replace("Information", "").trim();
	    accountNumbers.forEach(accountNumber -> {
	    	java.util.Optional<AobAccountInformation> accountInfo = accounts.stream()
	    			.filter(account -> account.getAccountNumber().equals(accountNumber))
	    			.findFirst();
	    	if(accountInfo.isPresent()) {
	    		unmappedSheetRows.put(ACCOUNT_SHEET_NAME, 
	    				new RowError(accountInfo.get().getRowIndex() - 1,
	    						"No matching Account in " + cleanSheet + " tab found - unmapped"));
	    	}

	    });
	}

	private TemplateImportResultBuilder<AobAccountInformation> convertToRelational(List<AobAccountInformation> accounts,
        List<AobSiteInformation> sites,
        List<AobContainerInformation> containers,
        List<AobRateInformation> rates) {

    Map<String, List<? extends Row>> sheetsPreview = new LinkedHashMap<>();
    sheetsPreview.put(ACCOUNT_SHEET_NAME, accounts);
    sheetsPreview.put(SITE_SHEET_NAME, sites);
    sheetsPreview.put(CONTAINER_SHEET_NAME, containers);
    sheetsPreview.put(RATE_SHEET_NAME, rates);

    Multimap<String, RowError> unmappedSheetRows = LinkedListMultimap.create();

    // sites
    Map<String, List<AobSiteInformation>> sitesMap = sites.stream()
            .collect(Collectors.groupingBy(AobSiteInformation::getParentKey));

    accounts.forEach(a -> {
       List<AobSiteInformation> siteList =  sitesMap.remove(a.getKey());
	   if (siteList != null) {
        a.setAobSites(siteList);
		a.getAobSites().forEach(site -> {
		site.setAobAccountInformation(a);
		});
		} else {
		logger.warn("No sites found for account key: {}", a.getKey());
		}
    });

    // containers
    Map<String, List<AobContainerInformation>> containersMap = containers.stream()
            .collect(Collectors.groupingBy(AobContainerInformation::getParentKey));

sites.forEach(s -> {
					List<AobContainerInformation> siteContainers = containersMap.remove(s.getKey());
					if (siteContainers != null) {
						s.setAobContainers(siteContainers);
						siteContainers.forEach(c -> {
							c.setAobSiteInformation(s);
							List<AobRateInformation> containerRates = c.getAobRates();
							if (containerRates != null) {
								containerRates.forEach(r -> {
									r.setAobSiteinformation(s);
								});
							}
						});
					} else {
						logger.warn("No containers found for site key: {}", s.getKey());
					}
				});


      // rates
	 Map<String, List<AobRateInformation>> ratesMap = rates.stream()
	 .peek(rate -> logger.debug("COMPANYYNUMBER: " + rate.getCompanyNumber()))
	 .collect(Collectors.groupingBy(AobRateInformation::getParentKey));

	containers.forEach(c -> {
 	List<AobRateInformation> rateList = ratesMap.remove(c.getKey());
 	if (rateList == null) {
	 	logger.warn("No rates found for container: " + c.getKey());
 	} else {
	 	c.setAobRates(rateList);
	 	rateList.forEach(r -> {
		 	r.setAobContainerInformation(c);
		 	r.setAobSiteinformation(c.getAobSiteInformation());
		 	r.setAobAccountInformation(c.getAobAccountInformation());
	 	});
 	}
});

    // Extracted logic for checking missing accounts
    checkMissingAccounts(accounts, sites, s -> ((AobSiteInformation) s).getAccountNumber(), SITE_SHEET_NAME, unmappedSheetRows);
    checkMissingAccounts(accounts, containers, c -> ((AobContainerInformation) c).getAccountNumber(), CONTAINER_SHEET_NAME, unmappedSheetRows);
    checkMissingAccounts(accounts, rates, r -> ((AobRateInformation) r).getAccountNumber(), RATE_SHEET_NAME, unmappedSheetRows);

    logger.debug("sites not mapped {}", sitesMap.values().size());
    logger.debug("containers not mapped {}", containersMap.values().size());
    logger.debug("rates not mapped {}", ratesMap.values().size());

    sitesMap.values().forEach(l -> {
        l.forEach(ll -> {
            unmappedSheetRows.put(SITE_SHEET_NAME,
                    new RowError(ll.getRowIndex() - 1, "No matching Account in Account tab found - unmapped"));
        });
    });

    containersMap.values().forEach(l -> {
        l.forEach(ll -> {
            unmappedSheetRows.put(CONTAINER_SHEET_NAME,
                    new RowError(ll.getRowIndex() - 1, "No matching Site in Site tab found - unmapped"));
        });
    });

    ratesMap.values().forEach(l -> {
        l.forEach(ll -> {
            unmappedSheetRows.put(RATE_SHEET_NAME,
                    new RowError(ll.getRowIndex() - 1, "No matching Container in Container tab found - unmapped"));
        });
    });

    TemplateImportResultBuilder<AobAccountInformation> builder = TemplateImportResult.<AobAccountInformation>builder()
            .unmappedRows(unmappedSheetRows.asMap());

    builder.accounts(accounts);
    builder.sheetsPreview(sheetsPreview);

    return builder;
}

	private List<? extends Row> trimForPreview(List<? extends Row> list) {
		return list.stream()
				.limit(MAX_ACCOUNTS_LIMIT).collect(Collectors.toList());
	}

	private void invokeSave(String projectId, String entity, List<?> batches)
			throws PersistenceException {
		
		if (batches != null) {
			batches.forEach(b -> {
				logger.info("ENTITIES: "+gson.toJson(b));
				String request = gson.toJson(new AobHandoff(projectId, SecurityUtils.getLoggedInUser(), entity, b));
				StoredProcCallResult result = new StoredProcCallResult();
				accountOnBoardingDao.aobSaveProjectData(request, result);
					if (!result.getErrorCode().trim().isEmpty())	 {
					logger.info("Result: {} - {}", result.getErrorCode().trim(), result.getErrorMessage().trim());
					logger.info("failed data: {}", b);
					throw new PersistenceException(new Exception(result.getErrorMessage()));
				}
			});
		}
	}

	public static int getSize(Collection<?> collection) {
		if (collection == null) {
			return 0;
		}
		return collection.size();
	}

	@Override
	protected void finalizeForPreview(ImportValidationResult res) {
		TemplateImportResult<AobAccountInformation> resiResult = (TemplateImportResult) res;
		for (Map.Entry<String, List<? extends Row>> entry : resiResult.getSheetsPreview().entrySet()) {
			resiResult.getSheetsPreview().put(entry.getKey(), trimForPreview(entry.getValue()));
		}
		logger.info("finilized for preview: ");
		logger.info("resiResult: "+resiResult.getSheetsPreview().toString());

	}

	@Override
	protected  boolean validateTemplateVersion(Workbook workbook) {
		Sheet sheet = workbook.getSheet("md");
		if (sheet == null) {
			return false;
		}
		
		DataFormatter dataFormatter = new DataFormatter();
		String metaID = dataFormatter.formatCellValue(sheet.getRow(0).getCell(0));
	
		return isTemplateValid(metaID);
	}

	@Override
	protected  boolean validateEmptyTemplate(Workbook workbook) {
		Sheet sheet = workbook.getSheet("Account Information");
		if (sheet == null) {
			return false;
		}
		
		DataFormatter dataFormatter = new DataFormatter();
		String emptyCell = dataFormatter.formatCellValue(sheet.getRow(5).getCell(0)); 
		if (emptyCell.equals("") || emptyCell.equals(null)){
			return false;
		}
	return true;
	}

	protected boolean isTemplateValid(String key){
		return TEMPLATE_KEY.equals(key);
	}

	public String uploadHistory(){
	return 	accountImportDao.getUploadHistory(new AobUploadHistoryRequest(SecurityUtils.getLoggedInUser(), "AOB"));
	}

	@Override
	public void scheduleUpload(String projectId, String scheduleDate, String scheduleTime) {
		throw new UnsupportedOperationException("Unimplemented method 'scheduleUpload'");
	}

	@Override
	public Object changeScheduleUpload(String projectId, String scheduleDate, String scheduleTime, String action) {
		throw new UnsupportedOperationException("Unimplemented method 'changeScheduleUpload'");
	}
}
