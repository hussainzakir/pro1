package com.repsrv.csweb.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.fasterxml.jackson.databind.ObjectMapper;

@Configuration
public class RestConfig {
	
	@Bean
	ObjectMapper restMapper() {
		return new ObjectMapper();
	}
}
