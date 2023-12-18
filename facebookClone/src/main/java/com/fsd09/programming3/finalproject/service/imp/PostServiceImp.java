package com.fsd09.programming3.finalproject.service.imp;

import com.fsd09.programming3.finalproject.entity.Comment;
import com.fsd09.programming3.finalproject.entity.Post;
import com.fsd09.programming3.finalproject.entity.User;
import com.fsd09.programming3.finalproject.mapper.PostResultMapper;
import com.fsd09.programming3.finalproject.repository.CommentRepository;
import com.fsd09.programming3.finalproject.repository.PostRepository;
import com.fsd09.programming3.finalproject.repository.UserRepository;
import com.fsd09.programming3.finalproject.result.PostResult;
import com.fsd09.programming3.finalproject.service.ICommentService;
import com.fsd09.programming3.finalproject.service.IPostService;
import com.fsd09.programming3.finalproject.util.IDGenerator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 *
 */
@Service
@RequiredArgsConstructor

@Slf4j
public class PostServiceImp implements IPostService {
    private final CommentRepository commentRepository;
    private final PostRepository postRepository;
    private final UserRepository userRepository;
    private final PostResultMapper postResultMapper;
    private final ICommentService commentService;

    @Override
    public void addNewPost(User user, String postContent) {
        Post post = new Post()
                .builder()
                .postId(IDGenerator.generatePostId())
                .postContent(postContent)
                .commentList(new ArrayList<>())
                .user(user)
                .build();
        user.getPostList().add(post);
        userRepository.save(user);
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
    public void deleteById(String postId, String userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new UsernameNotFoundException("user can not be found"));
        Post post = postRepository.findById(postId).orElseThrow(() -> new UsernameNotFoundException("Post can not be found"));
        boolean remove = user.getPostList().remove(post);
        if (post.getCommentList().size()!=0){
            ArrayList<Comment> copy = new ArrayList<>(post.getCommentList());

            for (Comment comment: copy
                 ) {
                commentService.delete(comment.getCommentId());
            }
        }
        userRepository.save(user);
        postRepository.delete(post);
    }

    @Override
    public void updatePost(String postId, String postContent) {
        Post post = postRepository.findById(postId).orElseThrow(() -> new UsernameNotFoundException("post cant be found"));
        post.setPostContent(postContent);
        postRepository.save(post);
    }
}
