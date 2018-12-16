package com.invillia.acme.providerservice;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.Collection;

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
import com.invillia.acme.providerservice.controller.ProviderRestController;
import com.invillia.acme.providerservice.model.domain.Provider;
import com.invillia.acme.providerservice.model.domain.ProviderAddress;
import com.invillia.acme.providerservice.service.ProviderService;

@RunWith(SpringRunner.class)
@WebMvcTest(ProviderRestController.class)
public class ProviderServiceApplicationTests {
	
	@Autowired
	private MockMvc mvc;
	
	@Autowired
	private ObjectMapper objectMapper;
	
	@MockBean
	private ProviderService providerSvc;
	
	private Provider provider;
	
	@Before
	public void setUp() {
		provider = new Provider("Provider Test", new ProviderAddress("Tv Perebebui", "Belém", "PA", "66069691", "Brasil"));
	}

	@Test
	public void givenProvider_whenSaveProvider_thenReturnJsonObject() throws Exception {
		when(providerSvc.save(any())).thenReturn(provider);
		
		mvc.perform(post("/api/provider")
				.contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(provider)))
		.andExpect(status().isOk())
		.andExpect(jsonPath("$.name", is("Provider Test")))
		.andDo(print());
	}
	
	@Test
	public void givenId_thenReturnJsonObject() throws Exception {
		when(providerSvc.findById(any())).thenReturn(provider);
		
		mvc.perform(get("/api/provider/"+1)
				.contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(provider)))
		.andExpect(status().isOk())
		.andExpect(jsonPath("$.name", is("Provider Test")))
		.andDo(print());
	}
	
	@Test
	public void givenName_thenReturnJsonArray() throws Exception {
		Collection<Provider> providers = new ArrayList<>();
		providers.add(provider);
		
		when(providerSvc.findByName(any())).thenReturn(providers);
		
		mvc.perform(get("/api/provider/find-by-name/"+"prov")
				.contentType(MediaType.APPLICATION_JSON))
		.andExpect(status().isOk())
		.andExpect(jsonPath("$", hasSize(1)))
		.andExpect(jsonPath("$[0].name", is("Provider Test")))
		.andDo(print());
	}

}

