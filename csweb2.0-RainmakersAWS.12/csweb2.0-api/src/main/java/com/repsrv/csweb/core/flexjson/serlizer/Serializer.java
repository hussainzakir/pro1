package com.repsrv.csweb.core.flexjson.serlizer;

import flexjson.JSONSerializer;
import flexjson.transformer.Transformer;

/**
 * Class that holds a serialization object and its identifier.
 */
public class Serializer{

    private String id;

    private JSONSerializer jsonSerializer;

    public Serializer(String id, JSONSerializer jsonSerializer){
        this.id = id;
        this.jsonSerializer = jsonSerializer;
    }

    public String serialize(Object object){
        if(jsonSerializer != null)
            return jsonSerializer.serialize(object);

        return null;
    }

    public Serializer addTransformer(Transformer transformer, Class clazz) {
    	if(jsonSerializer != null)
            this.jsonSerializer.transform(transformer, clazz);
    	
    	return this;
    }
    public String getId(){
        return id;
    }

    public void setId(String id){
        this.id = id;
    }

    public JSONSerializer getJsonSerializer(){
        return jsonSerializer;
    }

    public void setJsonSerializer(JSONSerializer jsonSerializer){
        this.jsonSerializer = jsonSerializer;
    }
}
