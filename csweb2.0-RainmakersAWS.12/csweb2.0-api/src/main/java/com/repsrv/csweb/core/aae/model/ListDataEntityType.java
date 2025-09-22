package com.repsrv.csweb.core.aae.model;

public enum ListDataEntityType {
    ACCOUNT("ACCOUNT"),
    SITE("SITE"),
    CONTAINER("CONTAINER"),
    RATE("RATE"),
    SALES("SALES"), 
    FSR("FSR");
    private String selected;

    private ListDataEntityType (String selected) {
        this.selected = selected;
    }

    public String getSelected() {
        return selected;
    }

    public static ListDataEntityType fromName(String selected){
        for(ListDataEntityType selection: ListDataEntityType.values()){
            if(selection.getSelected().equals(selected)){
                return selection;
            }
        }
        return null;
    }
}

