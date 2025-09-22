package com.repsrv.csweb.core.model.codes;


public enum StandardizedCodeType {
	ESC("C"),
	DSC("D");

	private String code;
	
	private StandardizedCodeType(String code){ this.code = code; }
	
	public String getCode() {return this.code;}
	
	public static StandardizedCodeType findByText(String code) {
		for(StandardizedCodeType codeType : StandardizedCodeType.values()) {
			if(codeType.toString().equalsIgnoreCase(code))
				return codeType;
		}
		return null;
	}
}
