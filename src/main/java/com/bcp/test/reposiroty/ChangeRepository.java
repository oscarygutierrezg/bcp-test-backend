package com.bcp.test.reposiroty;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bcp.test.model.Change;

@Repository
public interface ChangeRepository extends JpaRepository<Change, UUID> {
	

}
