package com.repsrv.csweb.core.account.imports.service;


import static com.repsrv.csweb.core.util.JsonUtil.fastJson;
import static org.apache.commons.collections.CollectionUtils.size;
import static org.apache.commons.lang.StringUtils.trim;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.apache.commons.collections4.ListUtils;
import org.apache.commons.lang3.StringUtils;
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
import com.google.gson.TypeAdapter;
import com.google.gson.TypeAdapterFactory;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;
import com.poiji.bind.Poiji;
import com.poiji.option.PoijiOptions;
import com.repsrv.csweb.core.account.imports.dao.AccountImportDao;
import com.repsrv.csweb.core.account.imports.validators.TemplateValidator;
import com.repsrv.csweb.core.model.account.imports.AccountInformation;
import com.repsrv.csweb.core.model.account.imports.ContainerInformation;
import com.repsrv.csweb.core.model.account.imports.DeleteStagingDataRequest;
import com.repsrv.csweb.core.model.account.imports.ImportMetaData;
import com.repsrv.csweb.core.model.account.imports.ImportValidationResult;
import com.repsrv.csweb.core.model.account.imports.RateInformation;
import com.repsrv.csweb.core.model.account.imports.ResiContractHandoff;
import com.repsrv.csweb.core.model.account.imports.TemplateImportResult;
import com.repsrv.csweb.core.model.account.imports.TemplateImportResult.TemplateImportResultBuilder;
import com.repsrv.csweb.core.model.account.imports.RouteInformation;
import com.repsrv.csweb.core.model.account.imports.Row;
import com.repsrv.csweb.core.model.account.imports.RowError;
import com.repsrv.csweb.core.model.account.imports.SiteInformation;
import com.repsrv.csweb.core.model.account.imports.TableWrapper;
import com.repsrv.csweb.core.model.account.onboarding.AobUploadHistoryRequest;
import com.repsrv.csweb.core.model.sp.StoredProcCallResult;
import com.repsrv.csweb.core.support.exception.SecurityUtils;
import com.repsrv.csweb.core.support.exception.massuploads.TemplateNotReadableException;
import com.repsrv.csweb.rest.exception.AccountImportSetupException;
import com.repsrv.csweb.rest.utils.CswebSecurityUtils;

@Service
public class ResiContractImportService extends AccountImportService<AccountInformation> implements ExcellSheetsConstants{

	

	private static final Logger logger = LoggerFactory.getLogger(ResiContractImportService.class);
	private static final int MAX_ACCOUNTS_LIMIT = 200;
	private static final String UPLOAD_FILENAME_FORMAT = "%s_%s_%s_%s"; // {CHG #}_{uploadTyoe}_{division}}
	private static final int BATCH_SIZE = 5500;
	private static final String DUPLICATE_KEY_ERROR_MSG = "Found duplicate value in row %s for column %s";
	private static final String TEMPLATE_KEY = "1707858278";

	@Autowired
	private AccountImportDao accountImportDao;
	
	private static  Map<String, String> gsonExcludes = new HashMap<>();
	private static Gson gson = null;
	
	@Autowired
	private ResiContractImportSetupService importSetupService;
	
	static {
	gson = TemplateValidator.gsonCreate();
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
		Sheet routesSheet = workbook.getSheet(ROUTE_SHEET_NAME);
		
		List<AccountInformation> accounts = Poiji.fromExcel(accountSheet, AccountInformation.class, options);
		List<SiteInformation> sites = Poiji.fromExcel(sitesSheet, SiteInformation.class, options);
		List<ContainerInformation> containers = Poiji.fromExcel(containerSheet, ContainerInformation.class, options);
		List<RateInformation> rates = Poiji.fromExcel(ratesSheet, RateInformation.class, options);
		List<RouteInformation> routes = Poiji.fromExcel(routesSheet, RouteInformation.class, options);
		
		logger.debug("Found {} account rows", size(accounts));
		logger.debug("Found {} site rows", size(sites));
		logger.debug("Found {} container rows", size(containers));
		logger.debug("Found {} rates rows", size(rates));
		logger.debug("Found {} routes rows", size(routes));
		
		TemplateImportResultBuilder resultBuilder = 
				convertToRelational(accounts, sites, containers, rates, routes);

		
		return resultBuilder.build();
	}

	@Override
	protected void validate(ImportValidationResult result) {
		TemplateImportResult<AccountInformation> resiResult = (TemplateImportResult) result;
		//change to sorted set multimap for ordering
		SetMultimap<String, RowError> tabViolations = LinkedHashMultimap.create();

		//change to sorted set multimap for ordering
		//SetMultimap<String, RowError> tabViolations = LinkedHashMultimap.create();
		
		resiResult.getAccounts().forEach(a -> {
			
			Set<ConstraintViolation<AccountInformation>> constraintViolations =
		            validator.validate(a);
			
			constraintViolations.forEach(v -> {
				Row row = (Row)v.getLeafBean();
				tabViolations.put(row.getTabName(), row.addViolation(v));
			});
		});
		
		//check for duplicates
		
		Map<String, Collection<RowError>> duplicateKeyErrors = validateForDupes(resiResult);
		for (Map.Entry<String, Collection<RowError>> entry : duplicateKeyErrors.entrySet()) {
    		Collection<RowError> cols = tabViolations.asMap().get(entry.getKey());
    		if(cols == null && entry.getValue() != null) {
        		tabViolations.putAll(entry.getKey(), entry.getValue());
    		} else if (cols != null) {
        		cols.addAll(entry.getValue());
   			}
		}
		
		
		
		Map<String, Collection<RowError>> origViolationMap = tabViolations.asMap();
		Map<String, Collection<RowError>> orderedViolations = new LinkedHashMap<>();
		Map<String, Collection<RowError>> orderedMappingViolations = new LinkedHashMap<>();
		for(String tabName : tabOrder) {
			if(origViolationMap.containsKey(tabName))
				orderedViolations.put(tabName, sort(origViolationMap.get(tabName)));
			
			if(resiResult.getUnmappedRows()!= null && resiResult.getUnmappedRows().containsKey(tabName)) {
				orderedMappingViolations.put(tabName, sort(resiResult.getUnmappedRows().get(tabName)));
			}
		}
		resiResult.setValidationErrors(orderedViolations);
		resiResult.setUnmappedRows(orderedMappingViolations);
	}

	private Collection<RowError> sort(Collection<RowError> collection) {
		List<RowError> list = new ArrayList<>(collection);
		 Collections.sort(list, Comparator.comparing(RowError::getRow));
		 return list;
	}

	private Map<String, Collection<RowError>> validateForDupes(TemplateImportResult resiResult) {
		Map<String, List<? extends Row>> sheetsData = resiResult.getSheetsPreview();
		Map<String, Collection<RowError>> errors = new HashMap<>();
		
		errors.put(ACCOUNT_SHEET_NAME, findDupeErros(sheetsData.get(ACCOUNT_SHEET_NAME)));
		errors.put(SITE_SHEET_NAME, findDupeErros(sheetsData.get(SITE_SHEET_NAME)));
		errors.put(CONTAINER_SHEET_NAME, findDupeErros(sheetsData.get(CONTAINER_SHEET_NAME)));
			
		return errors;
	}

	private Collection<RowError> findDupeErros(List<? extends Row> list) {
		Collection<RowError> errors = new ArrayList<>();
		if(list != null) {
			Map<String, Row> validRows = new HashMap<>();
			for(Row r : list) {
				Row existingRow = validRows.get(r.getKey());
				if(existingRow != null) {
					errors.add(
							new RowError(
									r.getRowIndex()-1
									,String.format(DUPLICATE_KEY_ERROR_MSG
									,existingRow.getRowIndex(), existingRow.getKeyName())));
				} else {
					validRows.put(r.getKey(), r);
				}
			}
		}
		return errors;
	}

	@Override
	public void persistData(
			List<?> accounts2,
			String changeNumber,
			String fileName,
			String uploadType,
			String aquisition,
			String projectId) throws AccountImportSetupException {

		List<AccountInformation> accounts = (List<AccountInformation>)accounts2;
		String div = StringUtils.leftPad(accounts.get(0).getCompanyNumber(), 3, '0');
		String fileNameFinal = String.format(UPLOAD_FILENAME_FORMAT, fileName, uploadType, div, aquisition);
		String user = CswebSecurityUtils.getLoggedInUser();
		
		ImportMetaData metadata = ImportMetaData.builder()
				.excel(fileNameFinal)
				.userid(user)
				.message("START")
				.build();
		
		ResiContractHandoff handoffRequest = ResiContractHandoff.builder()
				.accounts(accounts)
				.div(div)
				.changeNumber(changeNumber)
				.metadata(metadata)
				.build();
		
		accountImportDao.handOffResiContractUpload(handoffRequest);
		logger.info("Response is: {}", handoffRequest.toString());
		
		String returnObject = handoffRequest.getMetadata().getResultMessage();
		
		logger.info("MEssage json {}"+returnObject);
		
		//if 'READY_TO_START' then copy all data to appropriate tables
		if("OK_TO_COPY".equalsIgnoreCase(trim(handoffRequest.getMetadata().getResultMessage()))) {
			try{
				deleteStagingData(handoffRequest);
				saveStagingData(handoffRequest);
			}catch(PersistenceException pe) {
				throw new AccountImportSetupException("Error copying data to InfoPro. " + pe.getLocalizedMessage());
			}
			
			flagForProcessing(handoffRequest);
		} else {
			logger.info("Failed to get library and tables.  ErrorCode={}, ErrorMsg={}",
					handoffRequest.getErrorCode(), handoffRequest.getErrorMessage() );
			throw new AccountImportSetupException("Error setting up import. "+handoffRequest.getErrorMessage());
		}
	}

	private void deleteStagingData(ResiContractHandoff handoffRequest) {
		
		ImportMetaData metadata = handoffRequest.getMetadata();
		metadata.setMessage(null);
		
		DeleteStagingDataRequest req = DeleteStagingDataRequest.builder()
				.metadata(metadata)
				.build();
		
		accountImportDao.deleteStagingData(req);
		
	}

	private void flagForProcessing(ResiContractHandoff handoffRequest) {
		handoffRequest.getMetadata().setMessage("READY For InfoPro");
		accountImportDao.handOffResiContractUpload(handoffRequest);
	}
	
	private void checkMissingAccounts(List<AccountInformation> accounts, List<?> items, Function<Object, String> getAccountNumber, String sheetName, Multimap<String, RowError> unmappedSheetRows) {
	    Set<String> accountNumbers = accounts.stream().map(AccountInformation::getAccountNumber).collect(Collectors.toSet());
	    Set<String> itemAccountNumbers = items.stream().map(getAccountNumber).collect(Collectors.toSet());
	    accountNumbers.removeAll(itemAccountNumbers);
	    String cleanSheet = sheetName.replace("Information", "").trim();
	    accountNumbers.forEach(accountNumber -> {
	    	java.util.Optional<AccountInformation> accountInfo = accounts.stream()
	    			.filter(account -> account.getAccountNumber().equals(accountNumber))
	    			.findFirst();
	    	if(accountInfo.isPresent()) {
	    		unmappedSheetRows.put(ACCOUNT_SHEET_NAME, 
	    				new RowError(accountInfo.get().getRowIndex() - 1,
	    						"No matching Account in " + cleanSheet + " tab found - unmapped"));
	    	}
	    	
	    });
	}
	
	private TemplateImportResultBuilder convertToRelational(List<AccountInformation> accounts,
			List<SiteInformation> sites, 
			List<ContainerInformation> containers, 
			List<RateInformation> rates, 
			List<RouteInformation> routes) {
		
		Map<String, List<? extends Row>> sheetsPreview = new LinkedHashMap<>();
		sheetsPreview.put(ACCOUNT_SHEET_NAME, accounts);
		sheetsPreview.put(SITE_SHEET_NAME, sites);
		sheetsPreview.put(CONTAINER_SHEET_NAME, containers);
		sheetsPreview.put(RATE_SHEET_NAME, rates);
		sheetsPreview.put(ROUTE_SHEET_NAME, routes);

		Multimap<String, RowError> unmappedSheetRows = LinkedListMultimap.create(); 		

		//routes
		Map<String, List<RouteInformation>> routesMap = routes.stream()
				  .collect(Collectors.groupingBy(RouteInformation::getParentKey));
		
		//rates
		Map<String, List<RateInformation>> ratesMap = rates.stream()
				  .collect(Collectors.groupingBy(RateInformation::getParentKey));
		
		containers.forEach(c -> {
			c.setRates(ratesMap.remove(c.getKey()));
			c.setRoutes(routesMap.remove(c.getKey()));
			if(c.getRoutes() != null) {
				c.getRoutes().forEach(r -> {
					r.setContainer(c);
				});
			}
		});
		
		//container
		Map<String, List<ContainerInformation>> containersMap = containers.stream()
				  .collect(Collectors.groupingBy(ContainerInformation::getParentKey));
			
		sites.forEach(s -> {
			s.setContainers(containersMap.remove(s.getKey()));
		});	
		
		//sites	
		Map<String, List<SiteInformation>> sitesMap = sites.stream()
				  .collect(Collectors.groupingBy(SiteInformation::getParentKey));
		
		accounts.forEach(a -> {
			a.setSites(sitesMap.remove(a.getKey()));
		});
		
	    // Extracted logic for checking missing accounts
	    checkMissingAccounts(accounts, sites, s -> ((SiteInformation) s).getAccountNumber(), SITE_SHEET_NAME, unmappedSheetRows);
	    checkMissingAccounts(accounts, containers, c -> ((ContainerInformation) c).getAccountNumber(), CONTAINER_SHEET_NAME, unmappedSheetRows);
	    checkMissingAccounts(accounts, rates, r -> ((RateInformation) r).getAccountNumber(), RATE_SHEET_NAME, unmappedSheetRows);
	    checkMissingAccounts(accounts, routes, r -> ((RouteInformation) r).getAccountNumber(), ROUTE_SHEET_NAME, unmappedSheetRows);

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
		
		TemplateImportResultBuilder builder = TemplateImportResult.builder()
				.unmappedRows(unmappedSheetRows.asMap());
		
		builder.accounts(accounts);
		builder.sheetsPreview(sheetsPreview);
		
		return builder;
	}

	public static void main(String [] args) throws TemplateNotReadableException {
		String path = "C:\\Users\\suftan\\Downloads\\Resi_Contract_Standard_sample_multiple-tests.xlsx";
		File file = new File(path);
		
		 try (InputStream is = new FileInputStream(file))
	        {
			 ResiContractImportService s = new ResiContractImportService();
			 s.doValidation(is);
	        }
	        catch (IOException e) {
	            e.printStackTrace();
	        }
	}
	
	private List<?extends Row> trimForPreview(List<? extends Row> list){
		return list.stream()
		.limit(MAX_ACCOUNTS_LIMIT).collect(Collectors.toList());
	}
	

	private void saveStagingData(ResiContractHandoff handoffRequest) {
		logger.info("Copying data to Account Import library: {}", handoffRequest.getMetadata().getLibrary());
		
		String library = handoffRequest.getMetadata().getLibrary();
		String userId = CswebSecurityUtils.getLoggedInUser();
		
		//extract all objects
		List<AccountInformation> accts = handoffRequest.getAccounts();
		List<SiteInformation> sites = accts.stream().
				flatMap(a -> a.getSites().stream())
				.collect(Collectors.toList());
		
		List<ContainerInformation> containers = sites.stream().
				flatMap(s -> s.getContainers().stream())
				.collect(Collectors.toList());
		
		List<RateInformation> rates = containers.stream().
				flatMap(c -> c.getRates().stream())
				.collect(Collectors.toList());
		
		List<RouteInformation> routes = containers.stream().
				flatMap(c -> c.getRoutes().stream())
				.collect(Collectors.toList());
		
		//Create batches
		List<List<AccountInformation>> acctBatches = ListUtils.partition(accts, BATCH_SIZE);
		List<List<SiteInformation>> siteBatches = ListUtils.partition(sites, BATCH_SIZE);
		List<List<ContainerInformation>> containerBatches = ListUtils.partition(containers, BATCH_SIZE);
		List<List<RateInformation>> rateBatches = ListUtils.partition(rates, BATCH_SIZE);
		List<List<RouteInformation>> routeBatches = ListUtils.partition(routes, BATCH_SIZE);
		
		logger.debug("Saving {} account batches",getSize(acctBatches));
		logger.debug("Saving {} site batches",getSize(siteBatches));
		logger.debug("Saving {} container batches",getSize(containerBatches));
		logger.debug("Saving {} rate batches",getSize(rateBatches));
		logger.debug("Saving {} route batches",getSize(routeBatches));
		
		ImportMetaData metadata = handoffRequest.getMetadata();
		
		invokeSave(handoffRequest.getAccountTable(), metadata, acctBatches);
		invokeSave(handoffRequest.getSiteTable(), metadata, siteBatches);
		invokeSave(handoffRequest.getContainerTable(), metadata, containerBatches);
		invokeSave(handoffRequest.getRouteTable(), metadata, routeBatches);
		invokeSave(handoffRequest.getRateTable(), metadata, rateBatches);
		
	}
	
	private void invokeSave(String table, ImportMetaData metadata, List<?> batches ) 
			throws PersistenceException{

		if(batches != null) {
			metadata.setTable(table);
			batches.forEach(b -> {
			StoredProcCallResult result = new StoredProcCallResult ();
			String gsonString = gson.toJson(new TableWrapper(b));
			String metadataJson = fastJson(metadata);
			accountImportDao
					.doSaveData(metadataJson, gsonString, result);
			
			
			if(!"00000".equals(result.getSqlState())) {
				logger.info("Result: {} - {}", result.getSqlState().trim(), result.getErrorMessage().trim());
				logger.info("failed metadata: {}", metadataJson);
				logger.info("failed data: {}", gsonString);
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


	public static class NullStringToEmptyAdapterFactory<T> implements TypeAdapterFactory {
	    public <T> TypeAdapter<T> create(Gson gson, TypeToken<T> type) {

	        Class<T> rawType = (Class<T>) type.getRawType();
	        if (rawType != String.class) {
	            return null;
	        }
	        return (TypeAdapter<T>) new StringAdapter();
	    }
	}

	public static class StringAdapter extends TypeAdapter<String> {
	    public String read(JsonReader reader) throws IOException {
	        if (reader.peek() == JsonToken.NULL) {
	            reader.nextNull();
	            return "";
	        }
	        return reader.nextString();
	    }
	    public void write(JsonWriter writer, String value) throws IOException {
	        if (value == null) {
	            writer.value("");
	            return;
	        }
	        writer.value(value);
	    }
	}

	@Override
	protected void finalizeForPreview(ImportValidationResult res) {
		TemplateImportResult<AccountInformation> resiResult = (TemplateImportResult) res;
		for(Map.Entry<String, List<? extends Row>> entry :resiResult.getSheetsPreview().entrySet()) {
			resiResult.getSheetsPreview().put(entry.getKey(),trimForPreview(entry.getValue()));
		}
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
		
		if (emptyCell.equals("") || emptyCell == null){
			return false;
		}
	return true;
	}

	protected boolean isTemplateValid(String key){
		return TEMPLATE_KEY.equals(key);
	}

	public String uploadHistory(){
	return 	accountImportDao.getUploadHistory(new AobUploadHistoryRequest(SecurityUtils.getLoggedInUser(), "ARC"));
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
