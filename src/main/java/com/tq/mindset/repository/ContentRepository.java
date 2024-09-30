package com.tq.mindset.repository;

import com.tq.mindset.domain.Content;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContentRepository extends JpaRepository<Content, Long> {
    // Additional custom query methods can be defined here if needed
}