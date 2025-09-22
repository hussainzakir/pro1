package com.repsrv.csweb.core.account.servicerecordings.service;

import static org.apache.commons.collections.CollectionUtils.isEmpty;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.repsrv.csweb.core.account.servicerecordings.dao.ServiceRecordingDao;
import com.repsrv.csweb.core.account.servicerecordings.model.AddRecordingDto;
import com.repsrv.csweb.core.account.servicerecordings.model.AddRecordingRequest;
import com.repsrv.csweb.core.account.servicerecordings.model.F2NoteRequest;
import com.repsrv.csweb.core.account.servicerecordings.model.MpuDownRequest;
import com.repsrv.csweb.core.codes.service.StandardizedCodesService;
import com.repsrv.csweb.core.codes.service.StandardizedCodesService.CodeStatus;
import com.repsrv.csweb.core.model.account.AccountSiteContact;
import com.repsrv.csweb.core.model.account.MaintainRecording;
import com.repsrv.csweb.core.model.account.RecordingStatus;
import com.repsrv.csweb.core.model.account.ServiceRecording;
import com.repsrv.csweb.core.model.codes.StandardizedCode;
import com.repsrv.csweb.core.model.codes.StandardizedCodeType;
import com.repsrv.csweb.core.model.sp.StoredProcCallResult;
import com.repsrv.csweb.core.service.JsonResultService;
import com.repsrv.csweb.core.support.exception.recordings.AddRecordingException;
import com.repsrv.csweb.core.support.exception.recordings.ServiceInterruptionException;
import com.repsrv.csweb.core.util.AS400DateUtil;
import com.repsrv.csweb.rest.utils.CswebSecurityUtils;

@Service("serviceRecordingsService")
public class ServiceRecordingsServiceImpl extends JsonResultService implements ServiceRecordingsService {
	protected final Logger logger = LoggerFactory.getLogger(getClass());
	
	// Recording status'
	private static final String OPEN_STATUS = "O";
	private static final String CLOSED_STATUS = "C";
	private static final String BOTH_STATUS = "B";

	@Autowired
	private ServiceRecordingDao svcRecDao;
	
	@Autowired
	private StandardizedCodesService standardizedCodesService;
	
	private List<ServiceRecording> getRecordings(String key, String status){
		StoredProcCallResult result = new StoredProcCallResult();
		
		String recs = this.svcRecDao.getAccountServiceRecordings(key, status, result);
		logger.debug("JSON string result: {}", recs);
		return getJsonDataObject(recs, new TypeReference<List<ServiceRecording>>(){});
	}

	@Override
	public List<ServiceRecording> getAllAccountServiceRecordings(String company, String account, String site) {
		return this.getRecordings(getCompKey(company, account, site), BOTH_STATUS);
	}

	@Override
	public List<ServiceRecording> getOpenAccountServiceRecordings(String company, String account, String site) {
		return this.getRecordings(getCompKey(company, account, site), OPEN_STATUS);
	}

	@Override
	public List<ServiceRecording> getClosedAccountServiceRecordings(String company, String account, String site) {
		return this.getRecordings(getCompKey(company, account, site), CLOSED_STATUS);
	}

	private String getCompKey(String company, String account, String site) {
		account = StringUtils.leftPad(account, 7, '0');
		String key = company.concat(account).concat(site);
		
		return key;
	}

	@Override
	public List<StandardizedCode> getRecordingTypes(String company, StandardizedCodeType code) {
		List<StandardizedCode> types = this.standardizedCodesService
				.getStandardizedCodesByType(company == null ? " " : company, code, CodeStatus.ACTIVE);
		
		return types;
	}
	
	@Override
	public String getNextServiceRecordingNumber(String company, String date, String user) {
		StoredProcCallResult result = new StoredProcCallResult();
		
		this.svcRecDao.getNextRecordingNumber(company, user, date, result);
		
		if(StringUtils.isNotBlank(result.getErrorMessage())) {
			throw new RuntimeException("Failed to get next Recording number - " + result.getErrorMessage());
		}
		
		return String.valueOf(result.getLongOutput());
	}
	
	private MaintainRecording getRec(String key) {
		StoredProcCallResult result = new StoredProcCallResult();
		String recs = this.svcRecDao.getServiceRecording(key, result);
		logger.debug("Recording JSON string result: {}", recs);
		
		List<MaintainRecording> list =  getJsonDataObject(recs, new TypeReference<List<MaintainRecording>>(){});
		MaintainRecording contact = isEmpty(list) ? null : list.get(0);
		
		return contact;
	}
	
	@Override
	public MaintainRecording getServiceRecording(String company, String account, String site, String serviceRecording, String date) {
		return this.getRec(getRecordingKey(company, account, site, serviceRecording, date));
	}
	
	private String getRecordingKey(String company, String account, String site, String serviceRecording, String date) {
		account = StringUtils.leftPad(account, 7, '0');
		serviceRecording = StringUtils.leftPad(serviceRecording, 5, '0');
		String key = company.concat(account).concat(site).concat(serviceRecording).concat(date);
		
		return key;
	}
	
	//com.allied.cs.validation.RecordingValidation_Add
	@Override
	public String createRecording(AddRecordingDto recording) throws AddRecordingException, ServiceInterruptionException {
		String user = CswebSecurityUtils.getLoggedInUser();
		boolean isServiceInterruption = isMpuFlagSet(recording.getCompany(), recording.getRecordingType());

		
		if(recording.isJobRequest()) {
			/**
			 * 		serviceRequestResponse = new ServiceRequestResponse();
		
		serviceRequest.setCompany(site.getCompanyNumber());
		serviceRequest.setAccount(site.getAccountNumber());
		serviceRequest.setSite(site.getSiteNumber());
		serviceRequest.setContainer(Integer.valueOf(getJobRequest().getContainerGroup()));
		serviceRequest.setServiceCode(getJobRequest().getServiceCode());
		serviceRequest.setRequestedHaulDate(StringUtil.convertDateToString(getCallRecording().getToBeCompletedDate(),"yyyy/MM/dd"));
		serviceRequest.setNumberOfLifts(getJobRequest().getNumberOfLifts());
		serviceRequest.setPoNumber(getJobRequest().getCustomerPO());
		serviceRequest.setOrderSource("INFOPRO" + " " + SessionUtil.getUser().getUserID());
		serviceRequest.setReportedByName(getCallRecording().getReportedBy());
		serviceRequest.setNotes(getJobRequest().getNotes());
		serviceRequest.setCommitmentDate(StringUtil.convertDateToString(getCallRecording().getCommitmentDate(),"yyyy/MM/dd"));		
		//only populate reason code if required	
		if ("1".equals(getJobRequest().getSaveFlag()) && 
				!serviceRequest.getCommitmentDate().equals(serviceRequest.getRequestedHaulDate()) )
			serviceRequest.setReasonCode(getJobRequest().getReasonCode());
		else {
			serviceRequest.setReasonCode("");
		}
		serviceRequest.setSupplementalFlag(getJobRequest().getSupplementalFlag());
			 */
			return "Not implemented yet";
		} else {
			
			// create F2 notes
			if(isServiceInterruption) {
				createF2Note(recording);
				return "F2 Note Created";
			} else {
				String date = AS400DateUtil.toYearMonthDate(new Date());
				//String recNumber = this.getNextServiceRecordingNumber(recording.getCompany(), date, user);
				
				AddRecordingRequest request = new AddRecordingRequest()
						.setCompany(recording.getCompany())
						.setAccount(recording.getAccount())
						.setSite(recording.getSite())
						.setAssignedTo(recording.getAssignTo())
						.setDescription(recording.getDescription())
						.setEmployeeNumber(recording.getEmployee().getEmployeeNumber())
						.setEscalationLevel(recording.getEscalation())
						.setOriginatedBy("C")
						.setPriority(recording.getPriority())
						.setReportedBy(user)
						.setRequestType(recording.getRecordingType())
						.setResolution(recording.getResolution())
						.setRouteNumber("0")
						.setRouteTo(recording.getRouteTo())
						.setSiteNumber(recording.getSite())
						.setStatus(RecordingStatus.getFromString(recording.getStatus()))
						.setSystemGenerated("N")
						.setTruckNumber("0")
						.setUsername(user);
				
				logger.debug("Parms for SP: " + request);
				this.svcRecDao.addServiceRecording(request);
				
				if(request.getBigDecimalOutput() == null)
					throw new AddRecordingException(request.getErrorMessage());
				
				return request.getBigDecimalOutput().toString();
			}
		}
		
	}
	
	private void createF2Note(AddRecordingDto recording) {
		
		String planDate = recording.getCompletedDate().replace("/", "-");
		
		F2NoteRequest req = F2NoteRequest.builder(CswebSecurityUtils.getLoggedInUser())
				.groupId( UUID.randomUUID().toString())
				.jobName("CreateServiceRecording")
				.infoproDivision(recording.getCompany())
				.clientId("DOPS")
				.easJobId(UUID.randomUUID().toString())
				.accountId(recording.getAccount())
				.siteId(recording.getSite())
				.transactionCode("OPWX")
				.requestDescription(recording.getDescription())
				.resolutionComment(recording.getResolution())
				.departmentCode(recording.getDepartment())
				.openClose("CLOSE")
				.sourceSystem("ABCDEFGHIJKLMNOPQRST")
				.sourceSystemId("ABCDEFGHIJKLMNOPQRSTUVWXY")
				.subjectName(recording.getSubject())
				.priority(recording.getPriority())
				.planDate(planDate)
				.escalation("A")
				.route("")
				.driver("")
				.truck("")
				.build();

		this.svcRecDao.createF2Note(req);
		
		logger.info("F2Note results: resultStatus["+req.getSqlState()+"]  errorCode["+req.getErrorCode()+"]  errorMsg["+req.getErrorMessage()+"]" );
	}
	

	@Override
	public boolean isMpuFlagSet(String companyNumber, String serviceCode) {
		MpuDownRequest request = new MpuDownRequest(companyNumber, serviceCode);
		
		this.svcRecDao.getMpuDownFlag(request);
		
		return request.isDownFlagSet();
	}
}
