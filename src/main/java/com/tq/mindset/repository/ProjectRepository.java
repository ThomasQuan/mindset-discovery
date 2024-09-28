package com.tq.mindset.repository;

import com.tq.mindset.domain.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProjectRepository extends JpaRepository<Project, Long> {
    // Additional custom query methods can be defined here if needed
}