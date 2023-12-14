package com.fsd09.programming3.finalproject.repositoryTest;

import com.fsd09.programming3.finalproject.entity.Post;
import com.fsd09.programming3.finalproject.entity.User;
import com.fsd09.programming3.finalproject.mapper.PostResultMapper;
import com.fsd09.programming3.finalproject.mapper.UserResultMapper;
import com.fsd09.programming3.finalproject.repository.PostRepository;
import com.fsd09.programming3.finalproject.repository.UserRepository;
import com.fsd09.programming3.finalproject.result.UserResult;
import com.fsd09.programming3.finalproject.util.IDGenerator;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

/**
 *
 */
@SpringBootTest
@Slf4j
public class PostRepositoryTest {
    @Autowired
    private PostRepository postRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserResultMapper userResultMapper;
    @Autowired
    private PostResultMapper postResultMapper;
    private User user;

    @BeforeEach
    void beforeEach() {
        User user = new User()
                .builder()
                .userId(IDGenerator.generateUserId())
                .userName("test123")
                .password("test123")
                .email("test123@gmail.com")
                .commentList(new ArrayList<>())
                .postList(new ArrayList<>())
                .build();
        User save = userRepository.save(user);
        this.user = save;
    }

    @Test
    @Transactional
    void insertAndCheckPostRepository() {
        //given

        String postId = IDGenerator.generatePostId();
        Post postTest = new Post()
                .builder()
                .postId(postId)
                .postContent("this is a post test")
                .user(user)
                .commentList(new ArrayList<>())
                .build();
        user.getPostList().add(postTest);
        userRepository.save(user);
        //when
        User test123 = userRepository.findByUserNameIgnoreCase("test123").orElseThrow(() -> new UsernameNotFoundException("user can not be found"));
        UserResult userResult = userResultMapper.apply(test123);
        Post byId = postRepository.findById(postId).orElse(null);

        List<Post> postByUser = postRepository.findByUser(user);


        //then
        log.info("the user {} posted this post", postByUser.get(0).getUser().getUserName());
        log.info("the post content is {}", postByUser.get(0).getPostContent());
        log.info("the post is created at {}", postByUser.get(0).getPostTime());
        log.info("user total {}", userRepository.findAll().size());
        assertThat(byId).isNotNull();
    }
}
