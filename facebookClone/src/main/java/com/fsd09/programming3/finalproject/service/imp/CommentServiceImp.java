package com.fsd09.programming3.finalproject.service.imp;

import com.fsd09.programming3.finalproject.entity.Comment;
import com.fsd09.programming3.finalproject.entity.Post;
import com.fsd09.programming3.finalproject.entity.User;
import com.fsd09.programming3.finalproject.mapper.CommentResultMapper;
import com.fsd09.programming3.finalproject.repository.CommentRepository;
import com.fsd09.programming3.finalproject.repository.PostRepository;
import com.fsd09.programming3.finalproject.repository.UserRepository;
import com.fsd09.programming3.finalproject.result.CommentResult;
import com.fsd09.programming3.finalproject.result.PostResult;
import com.fsd09.programming3.finalproject.service.ICommentService;
import com.fsd09.programming3.finalproject.util.IDGenerator;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 *
 */
@Service

@RequiredArgsConstructor
@Size

public class CommentServiceImp implements ICommentService {
    private final CommentRepository commentRepository;
    private final UserRepository userRepository;
    private final PostRepository postRepository;
    private final CommentResultMapper commentResultMapper;

    @Override
    public void addNewComment(PostResult postResult, @NotEmpty String commentContent, String userId) {
        //get user entity
        User user = userRepository.findById(userId).orElseThrow(() -> new UsernameNotFoundException("user can not be found"));
        //get post entity
        Post post = postRepository.findById(postResult.postId()).orElseThrow(() -> new UsernameNotFoundException(("post can not be found")));
        //create new comment and link to user and post
        Comment comment = new Comment().builder()
                .commentId(IDGenerator.generateCommentId())
                .commentContent(commentContent)
                .user(user)
                .post(post)
                .build();
        user.getCommentList().add(comment);
        post.getCommentList().add(comment);
        userRepository.save(user);
        postRepository.save(post);
    }

    @Override
    public CommentResult getCommentById(String commentId) {
        Comment comment = commentRepository.findById(commentId).orElseThrow(() -> new UsernameNotFoundException("comment can not be found"));
        return commentResultMapper.apply(comment);
    }

    @Override
    public void updateComment(String commentId, String commentContent) {
        Comment comment = commentRepository.findById(commentId).orElseThrow(() -> new UsernameNotFoundException("comment can not be found"));
        comment.setCommentContent(commentContent);
        Comment save = commentRepository.save(comment);

    }

    @Override
    public void delete(String commentId) {
        Comment comment = commentRepository.findById(commentId).orElseThrow(() -> new UsernameNotFoundException("comment can not be found"));
        User user = comment.getUser();
        Post post = comment.getPost();
        user.getCommentList().remove(comment);
        userRepository.save(user);
        post.getCommentList().remove(comment);
        postRepository.save(post);
        commentRepository.delete(comment);
    }
}
