package com.tq.mindset.repository;

import com.tq.mindset.domain.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    // Additional custom query methods can be defined here if needed
}