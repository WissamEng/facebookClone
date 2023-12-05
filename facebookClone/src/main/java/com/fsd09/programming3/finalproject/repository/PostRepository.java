package com.fsd09.programming3.finalproject.repository;

import com.fsd09.programming3.finalproject.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 */
public interface PostRepository extends JpaRepository<Post, String> {
}
