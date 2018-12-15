package com.invillia.acme.paymentservice.configuration;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestOperations;

@Configuration
public class OrderRestServiceConfig {

	@Bean
	public RestOperations orderRestService(RestTemplateBuilder builder) {
		return builder.build();
	}
	
}
