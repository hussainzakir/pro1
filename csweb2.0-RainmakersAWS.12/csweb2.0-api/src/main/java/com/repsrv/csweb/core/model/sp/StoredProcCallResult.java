package com.repsrv.csweb.core.model.sp;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class StoredProcCallResult {

	@JsonIgnore
	private String changeRequired;
	@JsonIgnore
	private String errorMessage;
	@JsonIgnore
	private Integer sqlCode;
	@JsonIgnore
	private String sqlState;
	@JsonIgnore
	private String errorCode;
	@JsonIgnore
	private Integer integerOutput;
	@JsonIgnore
	private Double doubleOutput;
	@JsonIgnore
	private long longOutput;
	@JsonIgnore
	private String stringOutput;
	@JsonIgnore
	private BigDecimal bigDecimalOutput;
	@JsonIgnore
	private BigDecimal totalRowCount;
	
	public StoredProcCallResult() {}

	public String getChangeRequired() {
		return changeRequired;
	}

	public void setChangeRequired(String changeRequired) {
		this.changeRequired = changeRequired;
	}

	public Integer getSqlCode() {
		return sqlCode;
	}

	public void setSqlCode(Integer sqlCode) {
		this.sqlCode = sqlCode;
	}

	public String getSqlState() {
		return sqlState;
	}

	public void setSqlState(String sqlState) {
		this.sqlState = sqlState;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	public long getLongOutput() {
		return longOutput;
	}

	public void setLongOutput(long longOutput) {
		this.longOutput = longOutput;
	}

	public Double getDoubleOutput() {
		return doubleOutput;
	}

	public void setDoubleOutput(Double doubleOutput) {
		this.doubleOutput = doubleOutput;
	}

	public Integer getIntegerOutput() {
		return integerOutput;
	}

	public void setIntegerOutput(Integer integerOutput) {
		this.integerOutput = integerOutput;
	}

	public String getStringOutput() {
		return stringOutput;
	}

	public void setStringOutput(String stringOutput) {
		this.stringOutput = stringOutput;
	}

	public BigDecimal getBigDecimalOutput() {
		return bigDecimalOutput;
	}

	public void setBigDecimalOutput(BigDecimal bigDecimalOutput) {
		this.bigDecimalOutput = bigDecimalOutput;
	}

	public String getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}

	public BigDecimal getTotalRowCount() {
		return totalRowCount;
	}

	public void setTotalRowCount(BigDecimal totalRowCount) {
		this.totalRowCount = totalRowCount;
	}

	protected String getJson(Object obj) {
		ObjectMapper mapper = new ObjectMapper();
		try {
			return mapper.writeValueAsString(obj);
		} catch (JsonProcessingException e) {
			log.error("Failed to generate JSON string for request", e);
		}
		return "FAILED JSON SERIALIZATION";
	}
}
