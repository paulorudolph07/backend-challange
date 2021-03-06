package com.invillia.acme.providerservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.invillia.acme.providerservice.model.domain.Provider;
import com.invillia.acme.providerservice.service.ProviderService;

@RestController
@RequestMapping(value="/api/provider", produces=MediaType.APPLICATION_JSON_VALUE)
public class ProviderRestController {

	@Autowired
	private ProviderService providerSvc;
	
	@PostMapping
	public ResponseEntity<Provider> save(@RequestBody Provider provider) {
		return ResponseEntity.ok(providerSvc.save(provider));
	}
	
	@GetMapping(value="/{id}")
	public ResponseEntity<Provider> findById(@PathVariable Long id) {
		return ResponseEntity.ok(providerSvc.findById(id));
	}
	
	@GetMapping(value="/find-by-name/{name}")
	public ResponseEntity<Iterable<Provider>> findByName(@PathVariable String name) {
		return ResponseEntity.ok().body(providerSvc.findByName(name));
	}
	
}
