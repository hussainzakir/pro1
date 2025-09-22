package com.repsrv.csweb.core.util;

import java.io.IOException;

import org.codehaus.jackson.map.ObjectMapper;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class JsonUtil {
	private static final Gson gson = new GsonBuilder().create();
	
	private JsonUtil() {}
	
	public static String gsonString(Object obj) {
		return gson.toJson(obj);
	}
	
	public static String fastJson(Object obj) {
		ObjectMapper mapper = new ObjectMapper();
		try {
			return mapper.writeValueAsString(obj);
		} catch ( IOException e) {
			throw new RuntimeException("Failed to convert to Json");
		}
	}
	
}
