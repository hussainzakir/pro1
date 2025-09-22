package com.repsrv.csweb.core.account.pricing.model;

import java.util.Arrays;
import java.util.List;

public enum PriceIncreaseUploadType {
	PIC("Commercial", "C", "price-increase/CommercialTemplate.csv", new String[]{"Index", "Company", "Acct", "Site", "CGrp", "CType", "QTY", "Size",
            "Lifts", "Comp", "Charge Code", "Type", "Method", "Charge Rate",
            "New Rate", "Effective Date", "Tran Code", "Rsn Code", "NA", "UOM",
            "WMT", "NRD DATE", "INV MSG"}),
    PIR("Residential", "R", "price-increase/ResidentialTemplate.csv",  new String[]{"Index", "Company", "Acct", "Site", "CGrp", "CType", "QTY", "Size",
            "Lifts", "Charge Code", "Type", "Method", "Charge Rate",
            "New Rate", "Effective Date", "Tran Code", "Rsn Code",
            "NRD DATE", "INV MSG"});

    
    private String desc;
    private String code;
    private String templateName;
    private final String[] expectedHeaders;

    PriceIncreaseUploadType(String desc, String code, String templateName, String[] expectedHeaders) {
		this.desc = desc;
        this.code = code;
        this.templateName = templateName;
        this.expectedHeaders = expectedHeaders;

    }
    
    public String getDescription() {
        return desc;
    }
    
    public String getCode() {
        return code;
    }
    
    public String getTemplateName() {
        return templateName;
    }
    
    public String[] getExpectedHeaders() {
        return expectedHeaders;
    }
        
public static PriceIncreaseUploadType valueOfSafe(String type) {
        
        String typeUpper = type.toUpperCase();
        for(PriceIncreaseUploadType typeE : PriceIncreaseUploadType.values()) {
            if(typeE.code.equals(typeUpper))
                return typeE;
        }
        
        return null;

    }

}
