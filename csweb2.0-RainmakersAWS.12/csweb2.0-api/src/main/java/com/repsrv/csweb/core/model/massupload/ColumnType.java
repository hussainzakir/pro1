package com.repsrv.csweb.core.model.massupload;

import org.apache.commons.lang3.StringUtils;

import com.fasterxml.jackson.annotation.JsonCreator;

public enum ColumnType{

	CHAR, DEC, DATE, INT;
	
	@JsonCreator // This is the factory method and must be static
    public static ColumnType fromString(String string) {
        if(!StringUtils.isEmpty(string)) {
        	string = string.trim();
        	for(ColumnType t : ColumnType.values()) {
        		if(t.name().equalsIgnoreCase(string))
        			return t;
        	}
        }
        return null;
    }
}
