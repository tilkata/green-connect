package com.greenconnect.postservice.model;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Getter
@Setter
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "content_identifier")
    private String contentIdentifier;

    @Column(name ="content_text")
    private String contentText;

    @Column(name = "author_id")
    private Long authorId;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "post_tags", joinColumns = @JoinColumn(name = "post_id"))
    @Column(name = "tag")
    private Set<String> tags;

    private String category;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    private int likeCount;

    @Enumerated(EnumType.STRING)
    private Visibility visibility;

    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        updatedAt = LocalDateTime.now();
    }
}

