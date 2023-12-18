package com.fsd09.programming3.finalproject.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.List;

/**
 *
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "final_project_post")
public class Post {
    @Id
    @Column(name = "post_id")
    private String postId;
    @Column(name = "post_content", columnDefinition = "TEXT")
    private String postContent;
    @CreationTimestamp
    @Column(name = "post_time")
    private LocalDateTime postTime;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
    @OneToMany(mappedBy = "post", cascade = CascadeType.REMOVE,fetch = FetchType.EAGER)
    private List<Comment> commentList;
}
