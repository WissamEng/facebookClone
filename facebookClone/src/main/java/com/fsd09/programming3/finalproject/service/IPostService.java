package com.fsd09.programming3.finalproject.service;

import com.fsd09.programming3.finalproject.entity.User;
import com.fsd09.programming3.finalproject.result.PostResult;

import java.util.List;

/**
 *
 */
public interface IPostService {
    void addNewPost(User user, String string);
    List<PostResult> getAllPost();
    PostResult getPostById(String postId);
    void deleteById(String postId, String userId);
    void updatePost(String postId, String postContent);
}
