package com.repsrv.csweb.core.account.openmarket.service;

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
import org.apache.commons.lang.StringUtils;
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
import com.repsrv.csweb.core.account.openmarket.dao.ChangeScheduleReq;
import com.repsrv.csweb.core.account.openmarket.dao.OpenMarketDao;
import com.repsrv.csweb.core.account.openmarket.dao.OpenMarketRequestDriverReq;
import com.repsrv.csweb.core.account.openmarket.dao.ScheduleUploadRequest;
import com.repsrv.csweb.core.account.templatevalidator.TemplateValidator;
import com.repsrv.csweb.core.model.account.imports.ImportValidationResult;
import com.repsrv.csweb.core.model.account.imports.Row;
import com.repsrv.csweb.core.model.account.imports.RowError;
import com.repsrv.csweb.core.model.account.imports.TemplateImportResult;
import com.repsrv.csweb.core.model.account.imports.TemplateImportResult.TemplateImportResultBuilder;
import com.repsrv.csweb.core.model.account.onboarding.AobUploadHistoryRequest;
import com.repsrv.csweb.core.model.account.openmarket.OpenMarketAccountInformation;
import com.repsrv.csweb.core.model.account.openmarket.OpenMarketContainerInformation;
import com.repsrv.csweb.core.model.account.openmarket.OpenMarketCreateProjectRequest;
import com.repsrv.csweb.core.model.account.openmarket.OpenMarketDelRequest;
import com.repsrv.csweb.core.model.account.openmarket.OpenMarketDeleteRequest;
import com.repsrv.csweb.core.model.account.openmarket.OpenMarketHandOff;
import com.repsrv.csweb.core.model.account.openmarket.OpenMarketProjectCreationDTO;
import com.repsrv.csweb.core.model.account.openmarket.OpenMarketRateInformation;
import com.repsrv.csweb.core.model.account.openmarket.OpenMarketRouteInformation;
import com.repsrv.csweb.core.model.account.openmarket.OpenMarketSiteInformation;
import com.repsrv.csweb.core.model.json.CSWebAction;
import com.repsrv.csweb.core.model.sp.StoredProcCallResult;
import com.repsrv.csweb.core.support.exception.SecurityUtils;
import com.repsrv.csweb.rest.exception.AccountImportSetupException;
import com.repsrv.csweb.rest.utils.CswebSecurityUtils;

@Service
public class OpenMarketService extends AccountImportService<OpenMarketAccountInformation>
		implements ExcellSheetsConstants {

	private static final Logger logger = LoggerFactory.getLogger(OpenMarketService.class);
	private static final int MAX_ACCOUNTS_LIMIT = 200;
	private static final String UPLOAD_FILENAME_FORMAT = "%s_%s_%s"; // {filename #}_{uploadTyoe}_{projectName}}
	private static final int BATCH_SIZE = 5500;
	private static final String TEMPLATE_KEY = "1744837089";
	private static final String UPLOAD_TYPE = "AOM";
	@Autowired
	private OpenMarketDao openMarketDao;

	@Autowired
	private AccountImportDao accountImportDao;

	private static Gson gson = null;

	static {
		final Map<String, String> gsonExcludes = new HashMap<>();
		gsonExcludes.put("openMarketSiteInformation","");
		gsonExcludes.put("openMarketContainerInformation","");
		gsonExcludes.put("openMarketRateInformation","");
        gsonExcludes.put("openMarketRoutes","");
		gson = TemplateValidator.gsonCreate(gsonExcludes);
	}

	private static final PoijiOptions options = TemplateValidator.poijiOptions();

	private ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
	private Validator validator = factory.getValidator();

	@Override
	protected ImportValidationResult serialize(Workbook workbook) {

		Sheet accountSheet = workbook.getSheet(ACCOUNT_SHEET_NAME);
		Sheet sitesSheet = workbook.getSheet(SITE_SHEET_NAME);
		Sheet containerSheet = workbook.getSheet(CONTAINER_SHEET_NAME);
		Sheet ratesSheet = workbook.getSheet(RATE_SHEET_NAME);
		Sheet routeSheet = workbook.getSheet(ROUTE_SHEET_NAME);

		List<OpenMarketAccountInformation> accounts = Poiji.fromExcel(accountSheet, OpenMarketAccountInformation.class, options);
		List<OpenMarketSiteInformation> sites = Poiji.fromExcel(sitesSheet, OpenMarketSiteInformation.class, options);
		List<OpenMarketContainerInformation> containers = Poiji.fromExcel(containerSheet, OpenMarketContainerInformation.class,
				options);
		logger.debug("RATES:" + ratesSheet);
		logger.debug("CONTAINER: "+containerSheet);
		
		List<OpenMarketRateInformation> rates = Poiji.fromExcel(ratesSheet, OpenMarketRateInformation.class, options);
		List<OpenMarketRouteInformation> routes = Poiji.fromExcel(routeSheet, OpenMarketRouteInformation.class, options);

		logger.debug("Found {} account rows", size(accounts));
		logger.debug("Found {} site rows", size(sites));
		logger.debug("Found {} container rows", size(containers));
		logger.debug("Found {} rates rows", size(rates));
		logger.debug("Found {} routes rows", size(routes));

		TemplateImportResultBuilder<OpenMarketAccountInformation> resultBuilder = convertToRelational(accounts, sites, containers, rates, routes);

		return resultBuilder.build();
	}

	@Override
	protected void validate(ImportValidationResult result) {
		TemplateImportResult<OpenMarketAccountInformation> resiResult = (TemplateImportResult) result;
		// change to sorted set multimap for ordering
		SetMultimap<String, RowError> tabViolations = LinkedHashMultimap.create();

		resiResult.getAccounts().forEach(a -> {

			Set<ConstraintViolation<OpenMarketAccountInformation>> constraintViolations = validator.validate(a);

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
	public void persistData(List<?> acctsDeser,
							String fileName,
							String changeNumber,
							String uploadType,
							String aquisition,
							String projectId) throws AccountImportSetupException {

		List<OpenMarketAccountInformation> accounts = (List<OpenMarketAccountInformation>) acctsDeser;
		String fileNameFinal = String.format(UPLOAD_FILENAME_FORMAT, uploadType, aquisition, fileName);
		String user = CswebSecurityUtils.getLoggedInUser();


		List<OpenMarketAccountInformation> accts = accounts;
		List<OpenMarketSiteInformation> sites = accts.stream().flatMap(a -> a.getOpenMarketSiteInformation().stream())
				.collect(Collectors.toList());

		List<OpenMarketContainerInformation> containers = sites.stream().flatMap(s -> s.getOpenMarketContainerInformation().stream())
				.collect(Collectors.toList());

		List<OpenMarketRateInformation> rates = containers.stream().flatMap(c -> c.getOpenMarketRateInformation().stream())
				.collect(Collectors.toList());

		List<OpenMarketRouteInformation> routes = containers.stream().flatMap(c -> c.getOpenMarketRoutes().stream())
				.collect(Collectors.toList());

		if (StringUtils.isBlank(projectId)) {
			OpenMarketProjectCreationDTO openMarketProjectCreationDTO = new OpenMarketProjectCreationDTO(
					aquisition, fileName, changeNumber, uploadType,
					String.valueOf(accts.size()), String.valueOf(sites.size()),
					String.valueOf(containers.size()), String.valueOf(rates.size()), String.valueOf(routes.size())
			);

			logger.debug("PROJECT ID: " + gson.toJson(openMarketProjectCreationDTO));
			OpenMarketCreateProjectRequest openMarketCreateProjectRequest = new OpenMarketCreateProjectRequest(
					CSWebAction.INSERT, user, openMarketProjectCreationDTO
			);
			openMarketDao.openMarketProjectCreation(openMarketCreateProjectRequest);

			logger.debug("PROJECT ID: " + openMarketCreateProjectRequest.getProjectId());

			if (Objects.isNull(openMarketCreateProjectRequest.getProjectId()) || StringUtils.isBlank(openMarketCreateProjectRequest.getProjectId())) {
				throw new AccountImportSetupException("Error creating project ID. " + openMarketCreateProjectRequest.getReturnErrorMsg());
			}

			projectId = openMarketCreateProjectRequest.getProjectId().trim();

		} else {
			OpenMarketDelRequest openMarketDelRequest = new OpenMarketDelRequest();
			openMarketDelRequest.setProjectId(projectId);
			openMarketDelRequest.setProjectName(aquisition);
			openMarketDelRequest.setChangeRequest(changeNumber);
			openMarketDelRequest.setFileName(fileName);
			deleteAllData(openMarketDelRequest);
		}

		// Create batches
		List<List<OpenMarketAccountInformation>> acctBatches = ListUtils.partition(accts, BATCH_SIZE);
		List<List<OpenMarketSiteInformation>> siteBatches = ListUtils.partition(sites, BATCH_SIZE);
		List<List<OpenMarketContainerInformation>> containerBatches = ListUtils.partition(containers, BATCH_SIZE);
		List<List<OpenMarketRateInformation>> rateBatches = ListUtils.partition(rates, BATCH_SIZE);
		List<List<OpenMarketRouteInformation>> routeBatches = ListUtils.partition(routes, BATCH_SIZE);

		invokeSave(projectId, "account", acctBatches);
		invokeSave(projectId, "site", siteBatches);
		invokeSave(projectId, "container", containerBatches);
		invokeSave(projectId, "rate", rateBatches);
		invokeSave(projectId, "route", routeBatches);

		OpenMarketRequestDriverReq openMarketRequestDriverReq = new OpenMarketRequestDriverReq(user, projectId);
		logger.debug("OpenMarket Driver Req: "+openMarketRequestDriverReq.getJson());
		openMarketDao.openMarketRequestDriver(openMarketRequestDriverReq);
		logger.debug("Response error: "+openMarketRequestDriverReq.getReturnErrorMsg());
		logger.debug("Response status: "+openMarketRequestDriverReq.getReturnStatus());

		if (StringUtils.isNotBlank(openMarketRequestDriverReq.getReturnStatus())
				&& !"00000".equals(openMarketRequestDriverReq.getReturnStatus())) {
			throw new AccountImportSetupException("Error: " + openMarketRequestDriverReq.getReturnErrorMsg());
		}


	}

	private void checkMissingAccounts(List<OpenMarketAccountInformation> accounts, List<? extends Row> items, Function<Object, String> getAccountNumber, String sheetName, Multimap<String, RowError> unmappedSheetRows) {
	    Set<String> accountNumbers = accounts.stream().map(OpenMarketAccountInformation::getAccountNumber).collect(Collectors.toSet());
	    Set<String> itemAccountNumbers = items.stream().map(getAccountNumber).collect(Collectors.toSet());
	    accountNumbers.removeAll(itemAccountNumbers);
	    String cleanSheet = sheetName.replace("Information", "").trim();
	    accountNumbers.forEach(accountNumber -> {
	    	java.util.Optional<OpenMarketAccountInformation> accountInfo = accounts.stream()
	    			.filter(account -> account.getAccountNumber().equals(accountNumber))
	    			.findFirst();
	    	if(accountInfo.isPresent()) {
	    		unmappedSheetRows.put(ACCOUNT_SHEET_NAME, 
	    				new RowError(accountInfo.get().getRowIndex() - 1,
	    						"No matching Account in " + cleanSheet + " tab found - unmapped"));
	    	}

	    });
	}

	private TemplateImportResultBuilder<OpenMarketAccountInformation> convertToRelational(List<OpenMarketAccountInformation> accounts,
			List<OpenMarketSiteInformation> sites,
			List<OpenMarketContainerInformation> containers,
			List<OpenMarketRateInformation> rates,
			List<OpenMarketRouteInformation> routes) {

		Map<String, List<? extends Row>> sheetsPreview = new LinkedHashMap<>();
		sheetsPreview.put(ACCOUNT_SHEET_NAME, accounts);
		sheetsPreview.put(SITE_SHEET_NAME, sites);
		sheetsPreview.put(CONTAINER_SHEET_NAME, containers);
		sheetsPreview.put(RATE_SHEET_NAME, rates);
		sheetsPreview.put(ROUTE_SHEET_NAME, routes);

		Multimap<String, RowError> unmappedSheetRows = LinkedListMultimap.create();

		//Routes
		Map<String, List<OpenMarketRouteInformation>> routesMap = routes.stream()
				  .collect(Collectors.groupingBy(OpenMarketRouteInformation::getParentKey));
		
		// rates
		Map<String, List<OpenMarketRateInformation>> ratesMap = rates.stream().peek(rate -> logger.debug("COMPANYYNUMBER: "+rate.getCompanyNumber()))
				.collect(Collectors.groupingBy(OpenMarketRateInformation::getParentKey));		

		containers.forEach(c -> {
			c.setOpenMarketRoutes(routesMap.remove(c.getKey()));
			c.setOpenMarketRateInformation(ratesMap.remove(c.getKey()));
			if (c.getOpenMarketRateInformation() != null) {
				c.getOpenMarketRateInformation().forEach(r -> {
					r.setOpenMarketContainerInformation(c);
				});
			}
			if(c.getOpenMarketRoutes() != null) {
				c.getOpenMarketRoutes().forEach(r -> {
					r.setOpenMarketContainerInformationontainer(c);
				});
			}
		});


		// container
		Map<String, List<OpenMarketContainerInformation>> containersMap = containers.stream()
				.collect(Collectors.groupingBy(OpenMarketContainerInformation::getParentKey));

				sites.forEach(s -> {
					List<OpenMarketContainerInformation> siteContainers = containersMap.remove(s.getKey());
					if (siteContainers != null) {
						s.setOpenMarketContainerInformation(siteContainers);
						siteContainers.forEach(c -> {
							c.setOpenMarketSiteInformation(s);
							List<OpenMarketRateInformation> containerRates = c.getOpenMarketRateInformation();
							if (containerRates != null) {
								containerRates.forEach(r -> {
									r.setOpenMarketSiteInformation(s);
								});
							}
						});
					} else {
						logger.warn("No containers found for site key: {}", s.getKey());
					}
				});

		// sites
		Map<String, List<OpenMarketSiteInformation>> sitesMap = sites.stream()
				.collect(Collectors.groupingBy(OpenMarketSiteInformation::getParentKey));

				accounts.forEach(a -> {
					List<OpenMarketSiteInformation> siteList = sitesMap.remove(a.getKey());
					if (siteList != null) {
						a.setOpenMarketSiteInformation(siteList);
						a.getOpenMarketSiteInformation().forEach(site -> {
							site.setOpenMarketAccountInformation(a);
						});
					} else {
						logger.warn("No sites found for account key: {}", a.getKey());
					}
				});

		// Extracted logic for checking missing accounts
	    checkMissingAccounts(accounts, sites, s -> ((OpenMarketSiteInformation) s).getAccountNumber(), SITE_SHEET_NAME, unmappedSheetRows);
	    checkMissingAccounts(accounts, containers, c -> ((OpenMarketContainerInformation) c).getAccountNumber(), CONTAINER_SHEET_NAME, unmappedSheetRows);
	    checkMissingAccounts(accounts, rates, r -> ((OpenMarketRateInformation) r).getAccountNumber(), RATE_SHEET_NAME, unmappedSheetRows);
		checkMissingAccounts(accounts, routes, r -> ((OpenMarketRouteInformation) r).getAccountNumber(), ROUTE_SHEET_NAME, unmappedSheetRows);


		logger.debug("sites not mapped {}", sitesMap.values().size());
		logger.debug("containers not mapped {}", containersMap.values().size());
		logger.debug("rates not mapped {}", ratesMap.values().size());
		logger.debug("routes not mapped {}", routesMap.values().size());

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

		routesMap.values().forEach(l -> {
			l.forEach(ll -> {
				unmappedSheetRows.put(ROUTE_SHEET_NAME, 
						new RowError(ll.getRowIndex() - 1, "No matching Container in Container tab found - unmapped"));
			});
		});
		


		TemplateImportResultBuilder<OpenMarketAccountInformation> builder = TemplateImportResult.<OpenMarketAccountInformation>builder()
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
				logger.debug("ENTITIES: "+gson.toJson(b));
				String request = gson.toJson(new OpenMarketHandOff(projectId,entity, UPLOAD_TYPE, b,SecurityUtils.getLoggedInUser()));
				StoredProcCallResult result = new StoredProcCallResult();
				logger.debug("load and PARSE: "+ request);
				openMarketDao.openMarketSaveProjectData(request, result);
					if (!result.getErrorCode().trim().isEmpty())	 {
					logger.debug("Result: {} - {}", result.getErrorCode().trim(), result.getErrorMessage().trim());
					logger.debug("failed data: {}", b);
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
		TemplateImportResult<OpenMarketAccountInformation> resiResult = (TemplateImportResult) res;
		for (Map.Entry<String, List<? extends Row>> entry : resiResult.getSheetsPreview().entrySet()) {
			resiResult.getSheetsPreview().put(entry.getKey(), trimForPreview(entry.getValue()));
		}
		logger.debug("finilized for preview: ");
		logger.debug("resiResult: "+resiResult.getSheetsPreview().toString());

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
		return 	accountImportDao.getUploadHistory(new AobUploadHistoryRequest(SecurityUtils.getLoggedInUser(), UPLOAD_TYPE));
	}

	public String uploadAllHistory(String uploadType) {
		return accountImportDao.getUploadHistory(new AobUploadHistoryRequest(SecurityUtils.getLoggedInUser(), uploadType));
	}

	@Override
	public void scheduleUpload(String projectId, String scheduleDate, String scheduleTime) {
		String user = CswebSecurityUtils.getLoggedInUser();
		ScheduleUploadRequest scheduleUploadRequest = new ScheduleUploadRequest(user, projectId, scheduleDate, scheduleTime);
		accountImportDao.scheduleUpload(scheduleUploadRequest);
		
		logger.info("Response scheduleUploadInfoMessage is: {}", scheduleUploadRequest.getReturnErrorMsg());
		logger.info("Response scheduleUpload is: {}" , scheduleUploadRequest.getReturnStatus());
		scheduleUploadRequest.validateResponse();
	}

	@Override
	public Object changeScheduleUpload(String projectId, String scheduleDate, String scheduleTime, String action) {
		String user = CswebSecurityUtils.getLoggedInUser();
		ChangeScheduleReq changeScheduleReq = new ChangeScheduleReq(user, projectId, scheduleDate, scheduleTime, action);
		accountImportDao.changeScheduleUpload(changeScheduleReq);

		logger.info("Response changeUploadInfoMessage is: {}", changeScheduleReq.getReturnErrorMsg());
		logger.info("Response changeUpload is: {}" , changeScheduleReq.getReturnStatus());

		return changeScheduleReq;
	}
	
	 
	public void deleteAllData(OpenMarketDelRequest openMarketDelRequest) {
		OpenMarketDeleteRequest req = new OpenMarketDeleteRequest(CSWebAction.DELETE_ALL,
				CswebSecurityUtils.getLoggedInUser(), openMarketDelRequest);
			
		this.openMarketDao.openMarketDeleteStagingData(req);
			
		logger.info("Delete data Error Message is: {}", req.getReturnErrorMsg());
		logger.info("Delete data Status is: {}" , req.getReturnStatus());
			
		req.validateResponse();
	}
}
