package com.tq.mindset.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Data  // Lombok will generate getters, setters, toString, equals, and hashCode
@NoArgsConstructor  // Generates a no-argument constructor
@AllArgsConstructor // Generates an all-argument constructor
@Builder // Provides a builder pattern for creating instances
public class Blog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String description;
    private String subtitle;

    @Lob
    private String content;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "blog_tags",  // Define the join table name
            joinColumns = @JoinColumn(name = "blog_id"),  // Foreign key in blog_tags pointing to Blog
            inverseJoinColumns = @JoinColumn(name = "tag_id")  // Foreign key in blog_tags pointing to Tag
    )
    private Set<Tag> tags = new HashSet<>();

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private String author;

    @PrePersist
    private void onCreate() {
        createdAt = LocalDateTime.now();
        updatedAt = LocalDateTime.now();
    }

    @PreUpdate
    private void onUpdate() {
        updatedAt = LocalDateTime.now();
    }
}
