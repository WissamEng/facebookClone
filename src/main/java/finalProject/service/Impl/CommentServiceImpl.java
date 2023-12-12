package finalProject.service.Impl;

import finalProject.domain.Comment;
import finalProject.entity.CommentEntity;
import finalProject.entity.PostEntity;
import finalProject.exception.CommentNotFoundException;
import finalProject.exception.PostNotFoundException;
import finalProject.mapper.CommentMapperHelper;
import finalProject.repository.CommentRepository;
import finalProject.repository.PostRepository;
import finalProject.service.CommentService;
import jakarta.transaction.Transactional;

import java.util.List;
import java.util.Optional;

public class CommentServiceImpl implements CommentService {

    private final CommentRepository commentRepository;
    private final CommentMapperHelper commentMapperHelper;
    private final PostRepository postRepository;

    public CommentServiceImpl(CommentRepository commentRepository, CommentMapperHelper commentMapperHelper, PostRepository postRepository) {
        this.commentRepository = commentRepository;
        this.commentMapperHelper = commentMapperHelper;
        this.postRepository = postRepository;
    }

    @Override
    public List<Comment> getAllComments() {
        List<CommentEntity> commentEntities = commentRepository.findAll();
        return commentMapperHelper.convertCommentEntityListToCommentList (commentEntities);
    }

    @Override
    public List<Comment> getAllCommentsForPost(Long postId) {
        Optional<PostEntity> postEntityOptional = postRepository.findById(postId);

        if (postEntityOptional.isPresent()) {
            PostEntity postEntity = postEntityOptional.get();
            List<CommentEntity> commentEntities = postEntity.getComments();
            return commentMapperHelper.convertCommentEntityListToCommentList(commentEntities);
        } else {
            throw new PostNotFoundException("Post not found with id: " + postId);
        }
    }

    @Override
    @Transactional
    public Long saveComment(Comment comment) {
        CommentEntity commentEntity = commentMapperHelper.convertCommentToCommentEntity(comment);
        CommentEntity savedCom =  commentRepository.save(commentEntity);
        return savedCom.getId();
    }

    @Override
    public Comment findCommentById(Long comId) {
        Optional<CommentEntity> byId=commentRepository.findById(comId);
        if(byId.isPresent()){
            CommentEntity foundCom = byId.get();
            return commentMapperHelper.convertCommentEntityToComment(foundCom);
        }
        throw new CommentNotFoundException("There is no comment by id " + comId);
    }

    @Override
    public void deleteCommentById(Long comId) {commentRepository.deleteById(comId);

    }
}
