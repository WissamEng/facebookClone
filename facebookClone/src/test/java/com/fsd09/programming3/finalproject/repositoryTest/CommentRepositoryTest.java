package com.fsd09.programming3.finalproject.repositoryTest;

import com.fsd09.programming3.finalproject.entity.Comment;
import com.fsd09.programming3.finalproject.entity.Post;
import com.fsd09.programming3.finalproject.entity.User;
import com.fsd09.programming3.finalproject.mapper.CommentResultMapper;
import com.fsd09.programming3.finalproject.mapper.PostResultMapper;
import com.fsd09.programming3.finalproject.mapper.UserResultMapper;
import com.fsd09.programming3.finalproject.repository.CommentRepository;
import com.fsd09.programming3.finalproject.repository.PostRepository;
import com.fsd09.programming3.finalproject.repository.UserRepository;
import com.fsd09.programming3.finalproject.util.IDGenerator;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

/**
 *
 */
@SpringBootTest
@Slf4j
public class CommentRepositoryTest {
    @Autowired
    private PostRepository postRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserResultMapper userResultMapper;
    @Autowired
    private PostResultMapper postResultMapper;
    @Autowired
    private CommentRepository commentRepository;
    @Autowired
    private CommentResultMapper commentResultMapper;
    private User user;
    private Post post;

    @BeforeEach
    void beforeEach() {
        //create a user
        User user = new User()
                .builder()
                .userId(IDGenerator.generateUserId())
                .userName("test123")
                .password("test123")
                .email("test123@gmail.com")
                .commentList(new ArrayList<>())
                .postList(new ArrayList<>())
                .build();
        //create a post
        Post post = new Post()
                .builder()
                .postId(IDGenerator.generatePostId())
                .postContent("this is a post test")
                .user(user)
                .commentList(new ArrayList<>())
                .build();
        user.getPostList().add(post);
        User save = userRepository.save(user);
        this.user = save;
        this.post = post;
    }

    @Test
    @Transactional
    void insertAndCheckCommentRepository() {
        //given
        final String commentId = IDGenerator.generateCommentId();
        Comment testComment = new Comment()
                .builder()
                .commentId(commentId)
                .commentContent("test comment")
                .post(post)
                .user(user)
                .build();
        //when
        post.getCommentList().add(testComment);
        user.getCommentList().add(testComment);
        userRepository.save(user);
        postRepository.save(post);


        //then
        Comment byId = commentRepository.findById(commentId).orElse(null);
        log.info(postRepository.findById(post.getPostId()).get().getCommentList().get(0).getCommentId());
        assertThat(byId).isNotNull();


    }

}
