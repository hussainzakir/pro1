package com.repsrv.csweb.core.model.account.imports;

public enum AccountImportType {

	RCNTRT("Resi Contract", "ARC", "Resi_Contract_Standard_Template.xlsx"),
	AOB("Account OnBoarding", "AOB", "Account_OnBoarding_Standard_Template.xlsx"),
	OPENMARKET("Open Market", "AOM", "Open_Market_Template.xlsx");
	
	private String desc;
	private String code;
	private String templateName;

	AccountImportType(String desc, String code, String templateName) {
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
	
	public static AccountImportType valueOfSafe(String type) {
		
		String typeUpper = type.toUpperCase();
		for(AccountImportType typeE : AccountImportType.values()) {
			if(typeE.code.equals(typeUpper))
				return typeE;
		}
		
		return null;

	}

}
