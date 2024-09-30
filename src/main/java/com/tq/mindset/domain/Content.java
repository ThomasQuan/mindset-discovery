package com.tq.mindset.domain;

import com.tq.mindset.domain.enums.ContentStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Content {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false, unique = true)
    private String slug;

    @Column(columnDefinition = "TEXT")
    private String markdownContent;

    @Column(columnDefinition = "TEXT")
    private String htmlContent;

    @Enumerated(EnumType.STRING)
    private ContentStatus status;

    private int orderNo;

    private String objectAs = "paragraph";

    @ManyToMany
    @JoinTable(
            name = "content_assets",
            joinColumns = @JoinColumn(name = "content_id"),
            inverseJoinColumns = @JoinColumn(name = "asset_id")
    )
    private Set<Asset> assets;

    @OneToOne(mappedBy = "content", cascade = CascadeType.ALL)
    private Language language;

    @ManyToOne
    @JoinColumn(name = "blog_id")
    @lombok.ToString.Exclude
    @lombok.EqualsAndHashCode.Exclude
    private Blog blog;

    @ManyToOne
    @JoinColumn(name = "project_id")
    @lombok.ToString.Exclude
    @lombok.EqualsAndHashCode.Exclude
    private Project project;
}