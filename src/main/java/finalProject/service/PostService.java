package finalProject.service;

import finalProject.domain.Comment;
import finalProject.domain.Post;

import java.util.List;

public interface PostService {

    List<Post> getAllPosts();

    Long savePost(Post post);

    Post findPostById(Long postId);

    void deletePostById(Long postId);


}
