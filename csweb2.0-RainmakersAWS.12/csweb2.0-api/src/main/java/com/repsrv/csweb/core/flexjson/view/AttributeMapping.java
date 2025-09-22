package com.repsrv.csweb.core.flexjson.view;

import java.util.LinkedList;
import java.util.List;

/**
 * Class the represents an attribute mapping within a View.
 */
public class AttributeMapping{

    /**
     * The attribute prefix path from the root object.
     */
    private String prefix;

    /**
     * The class of the attribute represented by this class.
     */
    private Class<?> clazz;

    private final List<MappingRule> rules = new LinkedList<>();

    public AttributeMapping(String prefix){
        this.prefix = prefix;
    }

    public void addMappingRule(MappingRule rule){
        this.rules.add(rule);
    }

    public String getPrefix(){
        return prefix;
    }

    public void setPrefix(String prefix){
        this.prefix = prefix;
    }

    public List<MappingRule> getRules(){
        return rules;
    }

    public Class<?> getClazz(){
        return clazz;
    }

    public void setClazz(Class<?> clazz){
        this.clazz = clazz;
    }
}
