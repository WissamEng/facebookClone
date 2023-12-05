package com.fsd09.programming3.finalproject.service.imp;

import com.fsd09.programming3.finalproject.repository.CommentRepository;
import com.fsd09.programming3.finalproject.service.ICommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 */
@Service
public class CommentServiceImp implements ICommentService {
    private final CommentRepository commentRepository;
    @Autowired
    public CommentServiceImp(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }
}
