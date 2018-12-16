package com.invillia.acme.paymentservice.restclient;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestOperations;

@Component
public class OrderRestClient {

	@Autowired
	private RestOperations orderRestService;
	
	@Autowired
	private Environment env;
	
	public String payOrder(Long orderId) {
		 ResponseEntity<?> response = orderRestService.exchange(
				 env.getProperty("orderserver.hostname") + "/pay/" + orderId,
				 HttpMethod.POST, null, String.class);
		 return response.getBody().toString();
	}
	
}
