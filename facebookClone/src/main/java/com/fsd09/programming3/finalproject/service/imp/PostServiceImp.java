package com.fsd09.programming3.finalproject.service.imp;

import com.fsd09.programming3.finalproject.repository.PostRepository;
import com.fsd09.programming3.finalproject.service.IPostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 */
@Service
public class PostServiceImp implements IPostService {
    private final PostRepository postRepository;
    @Autowired
    public PostServiceImp(PostRepository postRepository) {
        this.postRepository = postRepository;
    }
}
