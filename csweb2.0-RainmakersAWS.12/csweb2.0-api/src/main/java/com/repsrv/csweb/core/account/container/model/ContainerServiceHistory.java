package com.repsrv.csweb.core.account.container.model;

import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown=true)
public class ContainerServiceHistory {

	@JsonProperty("Company")
	private String companyId; 
	
	@JsonProperty("Acct_Number")
	private String accountId;
	
	@JsonProperty("Site")
	private String siteId;
	
	@JsonProperty("Container_Grp")
	private String containerGroup;
	
	@JsonProperty("Lift_Time")
	private String liftTime;
	
	@JsonProperty("Service_Code")
	private String serviceCode;
	
	@JsonProperty("Date_Serviced")
	private String dateServiced;
	
	@JsonProperty("Route_Number")
	private String routeNumber;
	
	@JsonProperty("Container_Qty")
	private String containerQty;
	
	@JsonProperty("Container_Type")
	private String containerType;
	
	@JsonProperty("Container_Size")
	private String containerSize;
	
	@JsonProperty("Compacted")
	private String compacted;
	
	@JsonProperty("Special_Handling")
	private String specialHandling;
	
	@JsonProperty("PO_Number")
	private String poNumber;
	
	@JsonProperty("Extra_Lift")
	private String extraLift;
	 
	@JsonProperty("Print_Sequence")
	private String printSequence;
	
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        ContainerServiceHistory that = (ContainerServiceHistory) obj;
        return Objects.equals(companyId, that.companyId) &&
               Objects.equals(accountId, that.accountId) &&
               Objects.equals(siteId, that.siteId) &&
               Objects.equals(containerGroup, that.containerGroup) &&
               Objects.equals(liftTime, that.liftTime) &&
               Objects.equals(serviceCode, that.serviceCode) &&
               Objects.equals(dateServiced, that.dateServiced) &&
               Objects.equals(routeNumber, that.routeNumber) &&
               Objects.equals(containerQty, that.containerQty) &&
               Objects.equals(containerType, that.containerType) &&
               Objects.equals(containerSize, that.containerSize) &&
               Objects.equals(compacted, that.compacted) &&
               Objects.equals(specialHandling, that.specialHandling) &&
               Objects.equals(poNumber, that.poNumber) &&
               Objects.equals(extraLift, that.extraLift) &&
               Objects.equals(printSequence, that.printSequence);
    }

    @Override
    public int hashCode() {
        return Objects.hash(companyId, accountId, siteId, containerGroup, liftTime, serviceCode, dateServiced,
                            routeNumber, containerQty, containerType, containerSize, compacted,
                            specialHandling, poNumber, extraLift, printSequence);
    }
}
