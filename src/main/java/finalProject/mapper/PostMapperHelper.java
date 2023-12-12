package finalProject.mapper;

import com.fasterxml.jackson.databind.ObjectMapper;
import finalProject.domain.Comment;
import finalProject.domain.Post;
import finalProject.dto.PostDTO;
import finalProject.entity.CommentEntity;
import finalProject.entity.PostEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class PostMapperHelper {
    private final ObjectMapper mapper;

    @Autowired
    public PostMapperHelper(ObjectMapper mapper) {
        this.mapper = mapper;
    }

    public List<Post> convertPostEntityListToPostList(List<PostEntity> postEntities){
        List<Post> posts = new ArrayList<>();
        for(PostEntity postEntity: postEntities){
            posts.add(mapper.convertValue(postEntity, Post.class));
        }

        return posts;
    }

    public List<PostDTO> convertPostListToPostDTOList(List<Post> posts){
        List<PostDTO> postDTOs = new ArrayList<>();
        for(Post post: posts){
            postDTOs.add(mapper.convertValue(post, PostDTO.class));
        }

        return postDTOs;
    }

    public PostEntity convertPostToPostEntity(Post post) {
        return mapper.convertValue(post, PostEntity.class);
    }

    public Post convertPostDTOToPost(PostDTO postDTO) {
        return mapper.convertValue(postDTO, Post.class);
    }

    public Post convertPostEntityToPost(PostEntity postEntity){
        return mapper.convertValue(postEntity, Post.class);
    }

}
