package com.fsd09.programming3.finalproject.repository;

import com.fsd09.programming3.finalproject.entity.Post;
import com.fsd09.programming3.finalproject.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 *
 */
public interface PostRepository extends JpaRepository<Post, String> {
    long deleteByPostIdIgnoreCase(String postId);
    List<Post> findByUser(User user);
}
