package com.bcp.test.reposiroty.security;


import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bcp.test.model.security.User;

@Repository
public interface UserRepository extends JpaRepository<User, UUID> {
	
	User findByEmail(String email);
	
}
