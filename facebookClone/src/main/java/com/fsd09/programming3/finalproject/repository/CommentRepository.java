package com.fsd09.programming3.finalproject.repository;

import com.fsd09.programming3.finalproject.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 */
public interface CommentRepository extends JpaRepository<Comment, String> {
}
