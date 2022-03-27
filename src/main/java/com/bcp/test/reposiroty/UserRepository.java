package com.bcp.test.reposiroty;

import com.bcp.test.model.User;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, UUID> {
	
	User findByUsername(String username);
	
}