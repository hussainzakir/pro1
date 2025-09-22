package com.repsrv.csweb.core.model.aae;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Setter
@Getter
@JsonIgnoreProperties(ignoreUnknown = true)
public class Container {
	
	private String containerStagingId;
	
	private String companyNumber;
	
	@JsonProperty("containerGroup")
	private String containerId;
	
	private String accountType;
	
	@JsonProperty("accountTypeDesc")
	private String accountTypeDescription;
	
	private String containerType;
	
	private String containerSize;
	
	@JsonProperty("compactorFlag")
	private String compactor;
	
	@JsonProperty("containerQtyOrder")
	private String quantityOrder;
	
	private String containerQtyOnSite;
	
	private String closeDate;
	
	private String containerStatus;
	
	private String specialHandling;
	
	private String specialHandlingDescription;
	
	@JsonProperty("containerOwned")
	private String customerOwned;
	
	@JsonProperty("purchaseOrderRequired")
	private String poRequired;
	
	@JsonProperty("oncallFlag")
	private String onCall;
	
	@JsonProperty("recurringChargeFrequency")
	private String billingFrequency;
	
	@JsonProperty("revenueDistributionCode")
	private String revenueDistribution;
	
	@JsonProperty("sharedContainerID")
	private String sharedContainerId;
	
	private String gridNumber;
	
	@JsonProperty("contractGroupNumber")
	private String contractGroup;
	
	private String contractNumber;
	
	private String originalStartDate;
	
	private String startDate;
	
	private String removalDate;
	
	@JsonProperty("minNumberOfLifts")
	private String estimatedLifts;
	
	private String totalLifts;
	
	private String mondayRequired;
	
	private String tuesdayRequired;
	
	private String wednesdayRequired;
	
	private String thursdayRequired;
	
	private String fridayRequired;
	
	private String saturdayRequired;
	
	private String sundayRequired;
	
	private String mondayLiftDay;
	
	private String tuesdayLiftDay;
	
	private String wednesdayLiftDay;
	
	private String thursdayLiftDay;
	
	private String fridayLiftDay;
	
	private String saturdayLiftDay;
	
	private String sundayLiftDay;
	
	private String periodLength;
	
	private String periodUnit;
	
	private String disposalCode;
	
	private String unitOfMeasure;
	
	private String weightLimit;
	
	private String recurMnthsAdvBill;
	
	private String disposalRateRestriction;
	
	private String cityAccountNumber;
	
	private String receiptRequired;
	
	private String billedToDate;
	
	private String remoteMonitorFlag;
	
	private String rateRestrOperDate;
	
	private String lastServiceDate;
	
	private String containerIdCode;
	
	private String disposalPriceCode;
	
	private String stopCode;
	
	private String rateType;
	
	@JsonProperty("SDtransactionCode")
	private String sdtransactionCode;
	
	@JsonProperty("SDreasonCode")
	private String sdReasonCode;
	
	@JsonProperty("SDtransReasonDescription")
	private String sdTransReasonDescription;
	
	@JsonProperty("SDservicingRep")
	private String sdServicingRep;
	
	@JsonProperty("SDservicingRepName")
	private String sdServicingRepName;
	
	@JsonProperty("SDsigningRep")
	private String sdSigningRep;
	
	@JsonProperty("SDsigningRepName")
	private String sdSigningRepName;
	
	@JsonProperty("SDcompetitorCode")
	private String sdCompetitorCode;
	
	@JsonProperty("SDCSAContract#")
	private String sdCsaContractNumber;
	
	@JsonProperty("SDleadSource")
	private String sdLeadSource;
	
	@JsonProperty("SDleadSourceDescription")
	private String sdLeadSourceDescription;
	
	private String routeCreateDelUR;
	
	private String routeSchPermService;
	
	private String routeUrEffectiveDate;
	
	private String routeUrPlanDate;
	
	private String routeUrSchEffectiveDate;
	
	private String routeUrSchPlanDate;
	
	private String routePOnumber;
	
	private String routeNotes1;
	
	private String routeNotes2;
	
	private String routeNotes3;
	
	private String routeNotes4;
	
	private String routeNotes5;
	
	private String routeNotes6;
	
	private String routeNotes7;
	
	private String routeNotes;
	
	private String routeWeekDelayService;
	
	private String routeQuantity;
	
	private String routeRequestedDelivery;
	
	private String routeServiceCode;
	
	private String districtCode;
	
	private String contractDescription;
	
	private String contractName;
	
	private String fsrEffectiveDate;
	
	private String fsrQtyOnOrder;
	
	private String fsrTotalLifts;
	
	private String fsrTimeFrame;
	
	private String fsrTimeFrameRef;
	
	private String fsrOnCall;
	
	private String fsrMinLifts;
	
	private String idCode;
	
	private List<Rate> rates;
	
}
