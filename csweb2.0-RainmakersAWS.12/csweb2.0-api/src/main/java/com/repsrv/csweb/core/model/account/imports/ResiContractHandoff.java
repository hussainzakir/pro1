package com.repsrv.csweb.core.model.account.imports;

import java.util.List;

import com.repsrv.csweb.core.model.sp.StoredProcCallResult;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@AllArgsConstructor
@Builder
public class ResiContractHandoff extends StoredProcCallResult{
	
	
	//Metadata
	private String div;
	private String changeNumber;
	private List<AccountInformation> accounts;
	
	@Setter
	private String library;
	@Setter
	private String accountTable;
	@Setter
	private String siteTable;
	@Setter
	private String containerTable;
	@Setter
	private String rateTable;
	@Setter
	private String routeTable;
	@Setter
	private String message;
	@Setter
	private ImportMetaData metadata;
	
	@Override
	public String toString() {
		return "ResiContractHandoff [ message=" + message + ", library=" + library
				+ ", accountTable=" + accountTable + ", siteTable=" + siteTable + ", containerTable=" + containerTable
				+ ", rateTable=" + rateTable + ", routeTable=" + routeTable + ", getErrorMessage()=" + getErrorMessage()
				+ ", getErrorCode()=" + getErrorCode() + "]";
	}

}
