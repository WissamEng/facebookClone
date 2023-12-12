package finalProject.service;

import finalProject.domain.Comment;

import java.util.List;

public interface CommentService {

    List<Comment> getAllComments();

    List<Comment> getAllCommentsForPost(Long postId);
    Long saveComment(Comment comment);

    Comment findCommentById(Long comId);

    void deleteCommentById(Long comId);

}
