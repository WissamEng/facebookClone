package finalProject.controller;

import finalProject.domain.Comment;
import finalProject.dto.CommentDTO;
import finalProject.mapper.CommentMapperHelper;
import finalProject.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/comment")
public class CommentController {
    private final CommentService commentService;
    private final CommentMapperHelper commentMapperHelper;
    @Autowired
    public CommentController(CommentService commentService, CommentMapperHelper commentMapperHelper) {
        this.commentService = commentService;
        this.commentMapperHelper = commentMapperHelper;
    }
    
    @GetMapping("/list")
    public String listComment(Model theComments) {
        // call the service to bring the comments
        List<Comment> allComments = commentService.getAllComments();

        //convert comments to commentDTOS
        List<CommentDTO> commentDTOS = commentMapperHelper.convertCommentListToCommentDTOList(allComments);

        // Add the data (commentList) to the model
        theComments.addAttribute("comments", commentDTOS);

        //return the thymeleaf
        return "list-post";
    }
    
}
