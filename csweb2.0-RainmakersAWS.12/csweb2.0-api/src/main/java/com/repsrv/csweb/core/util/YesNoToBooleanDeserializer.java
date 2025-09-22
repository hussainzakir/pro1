package com.repsrv.csweb.core.util;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

public class YesNoToBooleanDeserializer extends JsonDeserializer<Boolean> {

	@Override
    public Boolean deserialize(JsonParser parser, DeserializationContext context) throws IOException, JsonProcessingException {
        return "Y".equalsIgnoreCase(parser.getText() == null ? "" : parser.getText().trim());
    }  
}
