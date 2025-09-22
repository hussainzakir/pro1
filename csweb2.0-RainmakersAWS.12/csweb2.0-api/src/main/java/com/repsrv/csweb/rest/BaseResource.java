package com.repsrv.csweb.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.repsrv.csweb.core.flexjson.SerializerFactory;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BaseResource{

    protected final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    @Qualifier("serializer")
    protected SerializerFactory serializerFactory;
    
}
