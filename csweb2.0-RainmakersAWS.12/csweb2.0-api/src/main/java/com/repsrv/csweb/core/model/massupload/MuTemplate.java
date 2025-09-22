package com.repsrv.csweb.core.model.massupload;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown=true)
public class MuTemplate {
	
	@JsonProperty("msutmpatid")
	private String id;
	
	@JsonProperty("status_code")
	private String status;

	@JsonProperty("template_description")
	private String description;

	private String changedBy;
	
	@JsonProperty("def_table_change")
	private String changedDate;
	
	@JsonProperty("detail_notes")
	private String detailDesc;
	
	@JsonProperty("system_to_run")
	private String system;
	
	private boolean showTemplateLink = true;
	
	@JsonProperty("columns")
	private List<ColumnDefinition> columnDefs;
	
	public MuTemplate() {}
	
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getChangedBy() {
		return changedBy;
	}
	public void setChangedBy(String changedBy) {
		this.changedBy = changedBy;
	}
	public String getChangedDate() {
		return changedDate;
	}
	public void setChangedDate(String changedDate) {
		this.changedDate = changedDate;
	}
	public String getDetailDesc() {
		return detailDesc;
	}
	public void setDetailDesc(String detailDesc) {
		this.detailDesc = detailDesc;
	}
	public String getSystem() {
		return system;
	}
	public void setSystem(String system) {
		this.system = system;
	}
	public boolean isShowTemplateLink() {
		return showTemplateLink;
	}
	
	@JsonProperty("display_template_download")
	public void setShowTemplateLink(String showTemplateLink) {
		this.showTemplateLink = "Y".equalsIgnoreCase(showTemplateLink);
	}

	public List<ColumnDefinition> getColumnDefs() {
		return columnDefs;
	}

	public void setColumnDefs(List<ColumnDefinition> columnDefs) {
		this.columnDefs = columnDefs;
	}

	@Override
	public String toString() {
		final int maxLen = 20;
		return "MuTemplate [status=" + status + ", id=" + id + ", description=" + description + ", changedBy="
				+ changedBy + ", changedDate=" + changedDate + ", detailDesc=" + detailDesc + ", system=" + system
				+ ", showTemplateLink=" + showTemplateLink + ", columnDefs="
				+ (columnDefs != null ? columnDefs.subList(0, Math.min(columnDefs.size(), maxLen)) : null) + "]";
	}
	
	public List<String> getColumnNames(){
		return this.columnDefs.stream()
				.map(ColumnDefinition::getTableFieldName)
				.collect(Collectors.toList());
	}
	
}
