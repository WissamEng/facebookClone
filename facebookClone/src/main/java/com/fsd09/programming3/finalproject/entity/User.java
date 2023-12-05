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
@Table(name = "final_project_user")
public class User {
    @Id
    @Column(name = "user_id")
    private String userId;
    @Column(name = "user_name")
    private String userName;
    @Column(name = "email")
    private String email;

    @Column(name = "create_Time")
    @CreationTimestamp
    private LocalDateTime createTime;
    @Column(name = "password")
    private String password;

    @OneToMany(mappedBy = "final_project_user", cascade = CascadeType.ALL)
    private List<Post> postList;
    @OneToMany(mappedBy = "final_project_user", cascade = CascadeType.ALL)
    private List<Comment> commentList;
}
