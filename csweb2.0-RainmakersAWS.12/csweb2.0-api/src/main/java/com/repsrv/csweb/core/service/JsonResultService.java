package com.repsrv.csweb.core.service;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.IOUtils;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ibm.as400.access.AS400JDBCClobLocator;
import com.repsrv.csweb.core.massuploads.service.MassUploadServiceImpl;
import com.repsrv.csweb.core.model.account.ServiceRecording;
import com.repsrv.csweb.core.model.massupload.MuTemplate;
import com.repsrv.csweb.core.util.StringTrimModule;

public class JsonResultService extends BaseService{
	
	protected final Logger logger = LoggerFactory.getLogger(JsonResultService.class);
	private static final ObjectMapper mapper = new ObjectMapper();
	
    static {
        //mapper.configure(MapperFeature.WRITE_NULL_MAP_VALUES, false);
        mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        mapper.registerModule(new StringTrimModule());
    }
	
    public <T> T getJsonDataObject(String jsonString, Class<T> type){
    	try {
			return toObject(jsonString, type);
		} catch (Exception e) {
			logger.error("Failed to convert JSON to object {}", type,e);
			logger.debug("Failed json {}", jsonString);
			return null;
		}
    }
    
	protected <T> T getJsonDataObject(Map dataMap, Class<T> type){
		if(dataMap != null && dataMap.containsKey("data")){

			AS400JDBCClobLocator clob = (AS400JDBCClobLocator)dataMap.get("data");
			try {
				String jsonString = IOUtils.toString(clob.getCharacterStream());
				return toObject(jsonString, type);
			} catch (IOException | SQLException e) {
				e.printStackTrace();
				return null;
			}
		}
		return null;
	}
	
	protected <T> String getJsonString(T object) {
		return toJson(object);
	}
	
	private <T> String toJson(T object) {
        try {
            return mapper.writeValueAsString(object);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
	
	@SuppressWarnings("unchecked")
	protected <T> List<T>  getJsonDataObject(String content, TypeReference<List<T>> typeReference) {
		if (content != null && !content.isEmpty()) {
            try {
                return (List<T>) mapper.readValue(content, typeReference);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        } else {
            return null;
        }
	}
 
    @SuppressWarnings("unchecked")
	protected <T> T toObject(String content, Class<T> clazz) {
        if (content != null && !content.isEmpty()) {
            try {
                return (T) mapper.readValue(content, clazz);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        } else {
            return null;
        }
    }
    

	@SuppressWarnings("unchecked")
	protected <T> T toObject(String content, TypeReference<T> tref) {
		 if (content != null && !content.isEmpty()) {
	            try {
	                return (T) mapper.readValue(content, tref);
	            } catch (Exception e) {
	                throw new RuntimeException(e);
	            }
	        } else {
	            return null;
	        }
	}
}
