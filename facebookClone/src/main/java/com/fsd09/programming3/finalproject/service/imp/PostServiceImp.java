package com.fsd09.programming3.finalproject.service.imp;

import com.fsd09.programming3.finalproject.entity.Post;
import com.fsd09.programming3.finalproject.entity.User;
import com.fsd09.programming3.finalproject.mapper.PostResultMapper;
import com.fsd09.programming3.finalproject.repository.PostRepository;
import com.fsd09.programming3.finalproject.repository.UserRepository;
import com.fsd09.programming3.finalproject.result.PostResult;
import com.fsd09.programming3.finalproject.service.IPostService;
import com.fsd09.programming3.finalproject.util.IDGenerator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 *
 */
@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class PostServiceImp implements IPostService {
    private final PostRepository postRepository;
    private final UserRepository userRepository;
    private final PostResultMapper postResultMapper;

    @Override
    public void addNewPost(User user, String postContent) {
        String userId = user.getUserId();
        User userEntity = userRepository.findById(userId).orElseThrow(() -> new UsernameNotFoundException("user can not be found"));
        Post post = new Post()
                .builder()
                .postId(IDGenerator.generatePostId())
                .postContent(postContent)
                .commentList(new ArrayList<>())
                .user(user)
                .build();
        Post save = postRepository.save(post);
    }

    @Override
    public List<PostResult> getAllPost() {
        List<Post> all = postRepository.findAll();
        all.sort(Comparator.comparing(Post::getPostTime).reversed());
        List<PostResult> postResultList = all.stream().map(postResultMapper).collect(Collectors.toList());
        return postResultList;
    }

    @Override
    public PostResult getPostById(String postId) {
        Post post = postRepository.findById(postId).orElseThrow(() -> new UsernameNotFoundException("post can not be found"));
        log.info(post.getPostContent());
        PostResult postResult = postResultMapper.apply(post);
        return postResult;
    }

    @Override
    public void deleteById(String postId) {
        long l = postRepository.deleteByPostIdIgnoreCase(postId);
        log.info(String.valueOf(l));
    }

    @Override
    public void updatePost(String postId, String postContent) {
        Post post = postRepository.findById(postId).orElseThrow(() -> new UsernameNotFoundException("post cant be found"));
        post.setPostContent(postContent);
    }
}
