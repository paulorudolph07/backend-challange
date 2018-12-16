package com.invillia.acme.providerservice.repository;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.invillia.acme.providerservice.model.domain.Provider;

@Repository
public interface IProviderRepository extends CrudRepository<Provider, Long> {

	@Query(value = "select p from Provider p where lower(p.name) like :name%")
	Collection<Provider> findByName(String name);
	
}
