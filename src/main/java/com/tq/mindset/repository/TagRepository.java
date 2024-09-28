package com.tq.mindset.repository;

import com.tq.mindset.domain.Tag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TagRepository extends JpaRepository<Tag, Long> {
    // Additional custom query methods can be defined here if needed
}
