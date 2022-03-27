package com.bcp.test.reposiroty;

import com.bcp.test.model.Change;
import com.bcp.test.model.Currency;
import com.bcp.test.model.User;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChangeRepository extends JpaRepository<Change, UUID> {
	

}