package com.invillia.acme.authserver.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.invillia.acme.authserver.model.Authority;

@Repository
public interface AuthorityRepository extends CrudRepository<Authority, String> {

	Authority findByName(String name);
	
}
