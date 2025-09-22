package com.repsrv.csweb.core.flexjson;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import com.repsrv.csweb.core.flexjson.serlizer.Serializer;
import com.repsrv.csweb.core.flexjson.serlizer.SerializerGenerator;
import com.repsrv.csweb.core.flexjson.serlizer.SerializerGeneratorImpl;
import com.repsrv.csweb.core.flexjson.view.View;
import com.repsrv.csweb.core.flexjson.view.ViewParser;

public class SerializerFactory{

    private final Map<String, Serializer> serializerMap;
    private SerializerGenerator serializerGenerator;

    private List<String> globalIncludes = new LinkedList<>();
    private List<String> globalExcludes = new LinkedList<>();
    private String resourcesPath;

    public SerializerFactory(){
        this.serializerMap = new HashMap<>();
    }

    public void init(){
        ViewParser viewParser = new ViewParser();
        List<View> views = viewParser.loadFileViews(this.resourcesPath);

        this.serializerGenerator = new SerializerGeneratorImpl(globalIncludes, globalExcludes);

        for(View view : views){
            if(view != null){
                Serializer serializer = this.serializerGenerator.generate(view);
                this.serializerMap.put(serializer.getId(), serializer);
            }
        }
    }

    public Serializer getSerializer(String id){
        Serializer serializer = this.serializerMap.get(id);
        if(serializer == null)
            throw new IllegalArgumentException("Cannot load serializer with the given identifier");

        return serializer;
    }

    /**
     * Serializes the given object using a JSON Serializer generated from the view describe within the second
     * parameter.
     *
     * @param object the object to serialize..
     * @param view   the view id to use wen serializing.
     * @return a json representation of {link object}
     */
    public String serialize(Object object, String view){
        return this.getSerializer(view).getJsonSerializer().serialize(object);
    }

    public Serializer createSerializer(View view){
        return this.serializerGenerator.generate(view);
    }

    public void setResourcesPath(String resourcesPath){
        this.resourcesPath = resourcesPath;
    }

    public void setGlobalIncludes(List<String> globalIncludes){
        this.globalIncludes = globalIncludes;
    }

    public void setGlobalExcludes(List<String> globalExcludes){
        this.globalExcludes = globalExcludes;
    }
}
