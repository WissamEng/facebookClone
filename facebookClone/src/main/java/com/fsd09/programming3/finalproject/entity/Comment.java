package com.fsd09.programming3.finalproject.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 *
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "comment")
public class Comment {
    @Id
    @Column(name = "comment_id")
    private String commentId;
    @Column(name = "comment_content", columnDefinition = "VARCHAR1000")
    private String commentContent;
    @Column(name = "comment_time")
    private LocalDateTime commentTime;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
    @ManyToOne
    @JoinColumn(name = "post_id")
    private Post post;


}
