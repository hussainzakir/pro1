package com.repsrv.csweb.core.model.listData;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Setter
@Getter
public class ListDataResponse {

    private List<ListDataValues> invoiceGroup;
    
    private List<ListDataValues> salesRep;
    
    private List<ListDataValues> chargeCode;
    
    private List<ListDataValues> latePayFeePolicy;
    
    private List<ListDataValues> stopCode;
    
    private List<ListDataValues> territory;
    
    private List<ListDataContainerSizeValues>cntrTypeSize;
    
    private List<ListDataValues> acquisitionCode;
    
    private List<ListDataValues> customerCategory;
    
    private List<ListDataValues> accountClass;
    
    private List<ListDataValues> residentialDistrict;
    
    private List<ListDataValues> contractStatus;
    
    private List<ListDataValues> servicingRep;
    
    private List<ListDataValues> signingRep;
    
    private List<ListDataValues> competitorCode;
        
    private List<ListDataTansactionReasonCodeValues> transactionReasonCode;
    
    private List<ListDataValues> leadsourceCode;
    
    private List<ListDataValues> chargeType;
    
    private List<ListDataValues> chargeMethod;
    
    private List<ListDataValues> revenueDisCode;
    
    private List<ListDataValues> rateType;
    
    @JsonProperty("recurringChargefreq")
    private List<ListDataValues> recurringChargeFreq;
    
    private List<ListDataValues> accountType;
    
    private List<ListDataValues> containerIdcode;
    
    private List<ListDataValues> specialHandling;
    
    private List<ListDataValues> receiptRequired;
    
    private List<ListDataDisposalCodeValues> disCdePriceCde;
    
    @JsonProperty("wasteMaterialtype")
    private List<ListDataValues> wasteMaterialType;
    
    private List<ListDataValues> uom;

    private List<ListDataValues> naics;

}
