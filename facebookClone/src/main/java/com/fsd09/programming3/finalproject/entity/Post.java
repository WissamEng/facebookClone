package com.fsd09.programming3.finalproject.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;

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
@Table(name = "post")
public class Post {
    @Id
    @Column(name = "post_id")
    private String postId;
    @Column(name = "post_content", columnDefinition = "TEXT")
    private String postContent;
    @Column(name = "post_time")
    private LocalDateTime postTime;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL)
    private List<Comment> commentList;
}
