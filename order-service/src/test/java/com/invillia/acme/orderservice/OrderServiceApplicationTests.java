package com.invillia.acme.orderservice;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.invillia.acme.orderservice.controller.OrderRestController;
import com.invillia.acme.orderservice.model.domain.Order;
import com.invillia.acme.orderservice.model.domain.OrderAddress;
import com.invillia.acme.orderservice.model.domain.OrderItem;
import com.invillia.acme.orderservice.service.OrderService;

@RunWith(SpringRunner.class)
@WebMvcTest(OrderRestController.class)
public class OrderServiceApplicationTests {
	
	@Autowired
	private MockMvc mvc;
	
	@Autowired
	private ObjectMapper objectMapper;
	
	@MockBean
	private OrderService orderService;
	
	private Order order;
	
	@SuppressWarnings("serial")
	@Before
	public void setUp() {
		order = new Order(new OrderAddress("Tv Perebebui", "Belém", "PA", "66069691", "Brasil"));
		
		OrderItem item = new OrderItem();
		item.setDescription("Book");
		item.setOrder(order);
		item.setPosition(0);
		item.setQuantity(2);
		item.setUnitPrice(new BigDecimal("23.89"));
		
		Set<OrderItem> items = new HashSet<OrderItem>() {{add(item);}};
		order.setItems(items);
	}

	@Test
	public void givenOrder_whenSaveOrder_thenReturnJsonObject() throws Exception {
		when(orderService.save(any())).thenReturn(order);
		
		mvc.perform(post("/api/order")
				.contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(order)))
		.andExpect(status().isOk())
		.andExpect(jsonPath("$.items", hasSize(1)))
		.andExpect(jsonPath("$.items[0].quantity", is(2)))
		.andDo(print());
	}
	
	@Test
	public void givenId_thenReturnJsonObject() throws Exception {
		when(orderService.findById(any())).thenReturn(order);
		
		mvc.perform(get("/api/order/"+1)
				.contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(order)))
		.andExpect(status().isOk())
		.andExpect(jsonPath("$.items", hasSize(1)))
		.andExpect(jsonPath("$.items[0].quantity", is(2)))
		.andDo(print());
	}
	
	@Test
	public void givenStatusId_thenReturnJsonArray() throws Exception {
		Collection<Order> orders = new ArrayList<>();
		orders.add(order);
		when(orderService.findAllByStatusId(any())).thenReturn(orders);
		
		mvc.perform(get("/api/order/find-by-status/"+1)
				.contentType(MediaType.APPLICATION_JSON))
		.andExpect(status().isOk())
		.andExpect(jsonPath("$", hasSize(1)))
		.andExpect(jsonPath("$[0].items[0].quantity", is(2)))
		.andDo(print());
	}
	
	@Test
	public void givenOrderId_whenPayOrder_thenReturnJsonObject() throws Exception {
		when(orderService.payOrder(any())).thenReturn(order);
		
		mvc.perform(post("/api/order/pay/"+1)
				.contentType(MediaType.APPLICATION_JSON))
		.andExpect(status().isOk())
		.andExpect(jsonPath("$.address.street", is("Tv Perebebui")))
		.andDo(print());
	}
	
	@Test
	public void givenOrderId_whenRefundOrder_thenReturnJsonObject() throws Exception {
		when(orderService.refundOrder(any())).thenReturn(order);
		
		mvc.perform(post("/api/order/refund/"+1)
				.contentType(MediaType.APPLICATION_JSON))
		.andExpect(status().isOk())
		.andExpect(jsonPath("$.address.street", is("Tv Perebebui")))
		.andDo(print());
	}
	
	@Test
	public void givenOrderItemId_whenRefundOrderItem_thenReturnJsonObject() throws Exception {
		when(orderService.refundOrderItem(any())).thenReturn(order.getItems().stream().findFirst().get());
		
		mvc.perform(post("/api/order/refund-item/"+1)
				.contentType(MediaType.APPLICATION_JSON))
		.andExpect(status().isOk())
		.andExpect(jsonPath("$.quantity", is(2)))
		.andDo(print());
	}

}

