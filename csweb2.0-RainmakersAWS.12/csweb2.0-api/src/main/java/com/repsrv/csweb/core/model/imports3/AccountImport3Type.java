package com.repsrv.csweb.core.model.imports3;

public enum AccountImport3Type {
    
    // RCNTRT("Resi Contract", "ARC", "Resi_Contract_Standard_Template.xlsx"),
	AOB("Account OnBoarding", "AOB", "Aob3_Template.xlsx");
	// OPENMARKET("Open Market", "AOM", "Open_Market_Template.xlsx");
	
	private String desc;
	private String code;
	private String templateName;

	AccountImport3Type(String desc, String code, String templateName) {
		this.desc = desc;
		this.code = code;
		this.templateName = templateName;
	}

	public String getDescription() {
		return desc;
	}
	
	public String getCode() {
		return code;
	}
	
	public String getTemplateName() {
		return templateName;
	}
	
	public static AccountImport3Type valueOfSafe(String type) {
		
		String typeUpper = type.toUpperCase();
		for(AccountImport3Type typeE : AccountImport3Type.values()) {
			if(typeE.code.equals(typeUpper))
				return typeE;
		}
		
		return null;

	}

}   
