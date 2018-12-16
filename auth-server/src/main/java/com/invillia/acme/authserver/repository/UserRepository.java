package com.invillia.acme.authserver.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.invillia.acme.authserver.model.User;

@Repository
public interface UserRepository extends CrudRepository<User, String> {

	@Query("select u from User u where lower(u.username) = lower(:username)")
    Optional<User> findByUsername( String username);
	
}
