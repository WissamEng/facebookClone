package finalProject.mapper;
import com.fasterxml.jackson.databind.ObjectMapper;
import finalProject.domain.Comment;
import finalProject.dto.CommentDTO;
import finalProject.entity.CommentEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class CommentMapperHelper {
    private final ObjectMapper mapper;

    @Autowired
    public CommentMapperHelper(ObjectMapper mapper) {
        this.mapper = mapper;
    }

    public List<Comment> convertCommentEntityListToCommentList(List<CommentEntity> commentEntities){
        List<Comment> comments = new ArrayList<>();
        for(CommentEntity commentEntity: commentEntities){
            comments.add(mapper.convertValue(commentEntity, Comment.class));
        }

        return comments;
    }

    public List<CommentDTO> convertCommentListToCommentDTOList(List<Comment> comments){
        List<CommentDTO> commentDTOs = new ArrayList<>();
        for(Comment comment: comments){
            commentDTOs.add(mapper.convertValue(comment, CommentDTO.class));
        }

        return commentDTOs;
    }

    public CommentEntity convertCommentToCommentEntity(Comment comment) {
        return mapper.convertValue(comment, CommentEntity.class);
    }

    public Comment convertCommentDTOToComment(CommentDTO commentDTO) {
        return mapper.convertValue(commentDTO, Comment.class);
    }

    public Comment convertCommentEntityToComment(CommentEntity commentEntity){
        return mapper.convertValue(commentEntity, Comment.class);
    }
}
