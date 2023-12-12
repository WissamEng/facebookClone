package finalProject.service.Impl;

import finalProject.domain.Comment;
import finalProject.domain.Post;
import finalProject.entity.CommentEntity;
import finalProject.entity.PostEntity;
import finalProject.exception.PostNotFoundException;
import finalProject.mapper.PostMapperHelper;
import finalProject.repository.PostRepository;
import finalProject.service.PostService;

import java.util.List;
import java.util.Optional;

public class PostServiceImpl implements PostService {

    private final PostRepository postRepository;
    private final PostMapperHelper postMapperHelper;

    public PostServiceImpl(PostRepository postRepository, PostMapperHelper postMapperHelper) {
        this.postRepository = postRepository;
        this.postMapperHelper = postMapperHelper;
    }

    @Override
    public List<Post> getAllPosts() {
        List<PostEntity> postEntities = postRepository.findAll();
        return postMapperHelper.convertPostEntityListToPostList (postEntities);
    }

    @Override
    public Long savePost(Post post) {
        PostEntity postEntity = postMapperHelper.convertPostToPostEntity(post);
        PostEntity savedPost =  postRepository.save(postEntity);
        return savedPost.getId();
    }

    @Override
    public Post findPostById(Long postId) {
        Optional<PostEntity> byId=postRepository.findById(postId);
        if(byId.isPresent()){
            PostEntity foundPost = byId.get();
            return postMapperHelper.convertPostEntityToPost(foundPost);
        }
        throw new PostNotFoundException("There is no post by id " + postId);
    }

    @Override
    public void deletePostById(Long postId) {
        postRepository.deleteById(postId);
    }



}
