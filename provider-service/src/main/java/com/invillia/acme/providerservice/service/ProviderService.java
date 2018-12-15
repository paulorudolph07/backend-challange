package com.invillia.acme.providerservice.service;

import java.util.Collection;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import com.invillia.acme.providerservice.model.domain.Provider;
import com.invillia.acme.providerservice.repository.IProviderRepository;

@Service
@Transactional
public class ProviderService {

	@Autowired
	private IProviderRepository providerRepo;
	
	public Provider save(@Validated Provider provider) {
		return providerRepo.save(provider);
	}
	
	@Transactional(readOnly = true)
	public Provider findById(Long id) {
		return providerRepo.findById(id).orElseThrow(() -> new EntityNotFoundException("Provider not found to id " + id));
	}
	
	public Collection<Provider> findByNameAndAddress(String name, String address) {
		return providerRepo.findByNameAndAddress(name, address);
	}
	
}
