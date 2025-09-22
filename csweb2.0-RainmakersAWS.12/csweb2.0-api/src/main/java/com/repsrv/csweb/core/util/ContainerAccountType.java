package com.repsrv.csweb.core.util;

import com.fasterxml.jackson.annotation.JsonFormat;

@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum ContainerAccountType {
	S("SEASONAL", "S - SEASONAL"), 
	T("TEMP", "T - TEMP"), 
	P("PERM", "P - PERM");

	private String label, detail;

	private ContainerAccountType(String label, String detail) {
		this.label = label;
		this.detail = detail;
	}

	public static ContainerAccountType stringToEnum(String typeString) {
		for (ContainerAccountType type : ContainerAccountType.values()) {
			if (type.name().equalsIgnoreCase(typeString))
				return type;
		}
		return null;
	}

	public String getLabel() {
		return this.label;
	}

	public String getDetail() {
		return this.detail;
	}
	
	public String toString() {
		return this.detail;
	}
}

