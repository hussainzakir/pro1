package com.repsrv.csweb.core.account.templatevalidator;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.gson.ExclusionStrategy;
import com.google.gson.FieldAttributes;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.TypeAdapter;
import com.google.gson.TypeAdapterFactory;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;
import com.poiji.option.PoijiOptions;
import com.repsrv.csweb.core.account.imports.service.ExcellSheetsConstants;
import com.repsrv.csweb.core.model.account.imports.ImportValidationResult;
import com.repsrv.csweb.core.model.account.imports.Row;
import com.repsrv.csweb.core.model.account.imports.RowError;

public class TemplateValidator implements ExcellSheetsConstants {
     private static final String DUPLICATE_KEY_ERROR_MSG = "Found duplicate value in row %s for column %s";
     private TemplateValidator(){}

     public static Gson gsonCreate(Map<String,String> gsonExcludes) {
	    
		ExclusionStrategy strategy = new ExclusionStrategy() {
		    @Override
		    public boolean shouldSkipField(FieldAttributes field) {
		        return gsonExcludes.containsKey(field.getName());
		    }

		    @Override
		    public boolean shouldSkipClass(Class<?> clazz) {
		        return false;
		    }
		};
		return new GsonBuilder()
				.addSerializationExclusionStrategy(strategy)
				.registerTypeAdapterFactory(new NullStringToEmptyAdapterFactory())
				.create();
    }

    public static class NullStringToEmptyAdapterFactory<T> implements TypeAdapterFactory {
	    public <T> TypeAdapter<T> create(Gson gson, TypeToken<T> type) {

	        Class<T> rawType = (Class<T>) type.getRawType();
	        if (rawType != String.class) {
	            return null;
	        }
	        return (TypeAdapter<T>) new StringAdapter();
	    }
	}
    public static class StringAdapter extends TypeAdapter<String> {
	    public String read(JsonReader reader) throws IOException {
	        if (reader.peek() == JsonToken.NULL) {
	            reader.nextNull();
	            return "";
	        }
	        return reader.nextString();
	    }
	    public void write(JsonWriter writer, String value) throws IOException {
	        if (value == null) {
	            writer.value("");
	            return;
	        }
	        writer.value(value);
	    }
	}

    public static final PoijiOptions poijiOptions() {
        return PoijiOptions.PoijiOptionsBuilder.settings()
            .disableXLSXNumberCellFormat()
            .caseInsensitive(true)
            .headerStart(0)
            .headerCount(3)
			.skip(2)
            .build();
    }

    
	public static Collection<RowError> sort(Collection<RowError> collection) {
		List<RowError> list = new ArrayList<>(collection);
		 Collections.sort(list, Comparator.comparing(RowError::getRow));
		 return list;
	}

    public static Map<String, Collection<RowError>> validateForDupes(ImportValidationResult resiResult) {
		Map<String, List<? extends Row>> sheetsData = resiResult.getSheetsPreview();
		Map<String, Collection<RowError>> errors = new HashMap<>();
		
		errors.put(ACCOUNT_SHEET_NAME,  findDupeErros(sheetsData.get(ACCOUNT_SHEET_NAME)));
		errors.put(SITE_SHEET_NAME, findDupeErros(sheetsData.get(SITE_SHEET_NAME)));
		errors.put(CONTAINER_SHEET_NAME, findDupeErros(sheetsData.get(CONTAINER_SHEET_NAME)));
			
		return errors;
	}
    
	private static Collection<RowError> findDupeErros(List<? extends Row> list) {
		Collection<RowError> errors = new ArrayList<>();
		if(list != null) {
			Map<String, Row> validRows = new HashMap<>();
			for(Row r : list) {
				Row existingRow = validRows.get(r.getKey());
				if(existingRow != null) {
					errors.add(
							new RowError(
									r.getRowIndex()-1
									,String.format(DUPLICATE_KEY_ERROR_MSG
									,existingRow.getRowIndex(), existingRow.getKeyName())));
				} else {
					validRows.put(r.getKey(), r);
				}
			}
		}
		return errors;
    }
}
