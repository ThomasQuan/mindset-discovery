package com.tq.mindset.repository;

import com.tq.mindset.domain.Blog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BlogRepository extends JpaRepository<Blog, Long> {
    // Additional custom query methods can be defined here if needed
}