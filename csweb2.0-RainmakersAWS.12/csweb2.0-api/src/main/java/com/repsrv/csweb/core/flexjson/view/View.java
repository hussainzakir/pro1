package com.repsrv.csweb.core.flexjson.view;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.repsrv.csweb.core.flexjson.serlizer.ClassUtil;



public class View{

    private static final String ID = "id";
    private static final String CLASS = "class";
    private static final String GLOBAL_INCLUDES = "global.includes";
    private static final String GLOBAL_EXCLUDES = "global.excludes";

    private final Map<String, AttributeMapping> mappings;
    private Class<?> clazz;
    private String id;

    private List<String> globalIncludes;
    private List<String> globalExcludes;

    public View(){
        this.mappings = new LinkedHashMap<>();
        this.globalExcludes = new LinkedList<>();
        this.globalIncludes = new LinkedList<>();
    }

    public void setProperty(ViewProperty viewProperty) throws ClassNotFoundException{
        switch(viewProperty.getName()){
            case ID:
                this.id = viewProperty.getValue();
                break;
            case CLASS:
                this.clazz = Class.forName(viewProperty.getValue(), false, getClass().getClassLoader());
                break;
            case GLOBAL_INCLUDES:
                String[] includes = viewProperty.getValue().split(",");
                this.globalIncludes = Arrays.stream(includes).map(String::trim).collect(Collectors.toList());
                break;
            case GLOBAL_EXCLUDES:
                String[] excludes = viewProperty.getValue().split(",");
                this.globalExcludes = Arrays.stream(excludes).map(String::trim).map(exclude -> "*." + exclude).collect(Collectors.toList());
                break;
        }
    }

    public void addMappingRule(MappingRule mappingRule){
        AttributeMapping mapping = this.mappings.get(mappingRule.getPrefix());
        if(mapping == null){
            mapping = new AttributeMapping(mappingRule.getPrefix());
            mapping.setPrefix(mappingRule.getPrefix());
            if(mapping.getPrefix().isEmpty())
                mapping.setClazz(this.clazz);
            else
                mapping.setClazz(ClassUtil.getAttributeClass(mappingRule.getPrefix(), null, clazz));
            this.mappings.put(mappingRule.getPrefix(), mapping);
        }

        mapping.addMappingRule(mappingRule);
    }

    public String getId(){
        return id;
    }

    public Map<String, AttributeMapping> getMappings(){
        return mappings;
    }

    public Class<?> getClazz(){
        return clazz;
    }

    public List<String> getGlobalIncludes(){
        return globalIncludes;
    }

    public List<String> getGlobalExcludes(){
        return globalExcludes;
    }
}
