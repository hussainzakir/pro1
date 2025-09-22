package com.repsrv.csweb.core.account.container.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.repsrv.csweb.core.model.json.BaseRequest;
import com.repsrv.csweb.core.model.json.CSWebAction;

public class HaulerCostInformation extends BaseRequest {

    @JsonProperty("haulerCostInformation")

    public HaulerInfo haulerInfo;

    public HaulerCostInformation(String userId, CSWebAction action, HaulerInfo haulerInfo) {
        super(userId, action);
        this.haulerInfo = haulerInfo;
    }

    public static class HaulerInfo {

        private String companyNumber;
        private String accountNumber;
        private String siteNumber;
        private String containerGroup;
        private String vendorNumber;
        private String vendorType;

        public String getCompanyNumber() {
            return companyNumber;
        }
        public String getAccountNumber() {
            return accountNumber;
        }
        public String getSiteNumber() {
            return siteNumber;
        }
        public String getContainerGroup() {
            return containerGroup;
        }
        public String getVendorNumber() {
            return vendorNumber;
        }

        public String getVendorType() {
            return vendorType;
        }

        public HaulerInfo(String companyNumber, String accountNumber, String siteNumber, String containerGroup,
            String vendorNumber, String vendorType) {
            this.companyNumber = companyNumber;
            this.accountNumber = accountNumber;
            this.siteNumber = siteNumber;
            this.containerGroup = containerGroup;
            this.vendorNumber = vendorNumber;
            this.vendorType = vendorType;
        }
    }

    @Override
    @JsonProperty(value = "haulerCostInformation")
    public Object getRequestedObject() {
        return this.haulerInfo;
    }
}
