package com.bcp.test.reposiroty.security;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bcp.test.model.security.Role;

public interface RoleRepository extends JpaRepository<Role, UUID> {

    Role findByName(String name);

    @Override
    void delete(Role role);

}
