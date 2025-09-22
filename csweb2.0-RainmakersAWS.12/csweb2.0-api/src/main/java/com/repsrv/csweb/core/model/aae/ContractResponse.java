package com.repsrv.csweb.core.model.aae;

import java.util.List;

public class ContractResponse {
	
	private List<ContractRates> rates;
	private List<ContractInfo> contract;
	
	public List<ContractRates> getRates() {
		return rates;
	}
	public void setRates(List<ContractRates> rates) {
		this.rates = rates;
	}
	public List<ContractInfo> getContract() {
		return contract;
	}
	public void setContract(List<ContractInfo> contract) {
		this.contract = contract;
	}
		
}
