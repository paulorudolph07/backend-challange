package com.invillia.acme.paymentservice;

import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.invillia.acme.paymentservice.controller.PaymentRestController;
import com.invillia.acme.paymentservice.model.domain.Payment;
import com.invillia.acme.paymentservice.service.PaymentService;

@RunWith(SpringRunner.class)
@WebMvcTest(PaymentRestController.class)
public class PaymentServiceApplicationTests {

	@Autowired
	private MockMvc mvc;
	
	@Autowired
	private ObjectMapper objectMapper;
	
	@MockBean
	private PaymentService paymentSvc;

	@Test
	public void givenPayment_whenPayOrder_thenReturnJsonObject() throws Exception {
		Payment payment = new Payment();
		payment.setCreditCardNumber("0000 0000 0000 0000");
		payment.setOrderId(1l);
		
		when(paymentSvc.payOrder(any())).thenReturn(payment);
		
		mvc.perform(post("/api/payment")
				.contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(payment)))
		.andExpect(status().isOk())
		.andExpect(jsonPath("$.creditCardNumber", is("0000 0000 0000 0000")))
		.andDo(print());
	}

}

