package com.repsrv.csweb.core.model.account;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown=true)
public class AccountReage {
	
	@JsonProperty("AgingCategory1")
	private String agingCategory1;
	
	@JsonProperty("AgingCategory2")
	private String agingCategory2;
	
	@JsonProperty("AgingCategory3")
	private String agingCategory3;
	
	@JsonProperty("AgingCategory4")
	private String agingCategory4;
	
	@JsonProperty("AgingCategory5")
	private String agingCategory5;
	
	@JsonProperty("AgingCategory6")
	private String agingCategory6;
	
	@JsonProperty("AgingCategory7")
	private String agingCategory7;
	
	@JsonProperty("AgingAmount1")
	private Double agingAmount1;
	
	@JsonProperty("AgingAmount2")
	private Double agingAmount2;
	
	@JsonProperty("AgingAmount3")
	private Double agingAmount3;
	
	@JsonProperty("AgingAmount4")
	private Double agingAmount4;
	
	@JsonProperty("AgingAmount5")
	private Double agingAmount5;
	
	@JsonProperty("AgingAmount6")
	private Double agingAmount6;
	
	@JsonProperty("AgingAmount7")
	private Double agingAmount7;
	
	public String getAgingCategory1() {
		return agingCategory1;
	}

	public void setAgingCategory1(String agingCategory1) {
		this.agingCategory1 = agingCategory1;
	}

	public String getAgingCategory2() {
		return agingCategory2;
	}

	public void setAgingCategory2(String agingCategory2) {
		this.agingCategory2 = agingCategory2;
	}

	public String getAgingCategory3() {
		return agingCategory3;
	}

	public void setAgingCategory3(String agingCategory3) {
		this.agingCategory3 = agingCategory3;
	}

	public String getAgingCategory4() {
		return agingCategory4;
	}

	public void setAgingCategory4(String agingCategory4) {
		this.agingCategory4 = agingCategory4;
	}

	public String getAgingCategory5() {
		return agingCategory5;
	}

	public void setAgingCategory5(String agingCategory5) {
		this.agingCategory5 = agingCategory5;
	}

	public String getAgingCategory6() {
		return agingCategory6;
	}

	public void setAgingCategory6(String agingCategory6) {
		this.agingCategory6 = agingCategory6;
	}

	public String getAgingCategory7() {
		return agingCategory7;
	}

	public void setAgingCategory7(String agingCategory7) {
		this.agingCategory7 = agingCategory7;
	}

	public Double getAgingAmount1() {
		return agingAmount1;
	}

	public void setAgingAmount1(Double agingAmount1) {
		this.agingAmount1 = agingAmount1;
	}

	public Double getAgingAmount2() {
		return agingAmount2;
	}

	public void setAgingAmount2(Double agingAmount2) {
		this.agingAmount2 = agingAmount2;
	}

	public Double getAgingAmount3() {
		return agingAmount3;
	}

	public void setAgingAmount3(Double agingAmount3) {
		this.agingAmount3 = agingAmount3;
	}

	public Double getAgingAmount4() {
		return agingAmount4;
	}

	public void setAgingAmount4(Double agingAmount4) {
		this.agingAmount4 = agingAmount4;
	}

	public Double getAgingAmount5() {
		return agingAmount5;
	}

	public void setAgingAmount5(Double agingAmount5) {
		this.agingAmount5 = agingAmount5;
	}

	public Double getAgingAmount6() {
		return agingAmount6;
	}

	public void setAgingAmount6(Double agingAmount6) {
		this.agingAmount6 = agingAmount6;
	}

	public Double getAgingAmount7() {
		return agingAmount7;
	}

	public void setAgingAmount7(Double agingAmount7) {
		this.agingAmount7 = agingAmount7;
	}

}
