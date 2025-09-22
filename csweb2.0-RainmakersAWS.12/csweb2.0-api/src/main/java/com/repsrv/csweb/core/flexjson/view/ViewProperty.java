package com.repsrv.csweb.core.flexjson.view;

/**
 * Represents a view property or attribute to be mapped for serialization.
 */
public class ViewProperty{

    private String name;

    private String value;

    public ViewProperty(String name, String value){
        this.name = name;
        this.value = value;
    }

    public String getName(){
        return name;
    }

    public void setName(String name){
        this.name = name;
    }

    public String getValue(){
        return value;
    }

    public void setValue(String value){
        this.value = value;
    }
}
