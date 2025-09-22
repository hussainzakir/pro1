package com.repsrv.csweb.core.model.massupload;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Setter
@Getter
@JsonIgnoreProperties(ignoreUnknown=true)
public class ColumnDefinition {

	@JsonProperty("msutdefid")
	private int id;
	
	@JsonProperty("status_code")
	private String status;
	
	@JsonProperty("column_name")
	private String name;
	
	@JsonProperty("column_description")
	private String description;
	
	@JsonProperty("column_data_type")
	private ColumnType columnType;
	
	@JsonProperty("column_lenght")
	private int length;
	
	@JsonProperty("column_decimals")
	private int decimalPlaces;
	
	private Boolean required;
	
	@JsonProperty("table_field_name")
	private String tableFieldName;
	
	@JsonProperty("default_value")
	private String defaultValue;

	@JsonProperty("column_required")
	public void setRequired(String requiredChar) {
		this.required = "1".equals(requiredChar);
	}

	@Override
	public String toString() {
		return "ColumnDefinition [id=" + id + ", status=" + status + ", name=" + name + ", description=" + description
				+ ", columnType=" + columnType + ", length=" + length + ", decimalPlaces=" + decimalPlaces
				+ ", required=" + required + ", tableFieldName=" + tableFieldName + ", defaultValue=" + defaultValue
				+ "]";
	}
	
	
}
