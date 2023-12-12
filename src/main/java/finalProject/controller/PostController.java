package finalProject.controller;

import finalProject.domain.Post;
import finalProject.dto.PostDTO;
import finalProject.mapper.PostMapperHelper;
import finalProject.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/post")
public class PostController {
    
    private final PostService postService;
    private final PostMapperHelper postMapperHelper;

    @Autowired
    public PostController(PostService postService, PostMapperHelper postMapperHelper) {
        this.postService = postService;
        this.postMapperHelper = postMapperHelper;
    }
    
    
    @GetMapping("/list")
    public String listPost(Model thePostsList) {
        // call the service to bring the posts
        List<Post> allPosts = postService.getAllPosts();

        //convert posts to postDTOS
        List<PostDTO> postDTOS = postMapperHelper.convertPostListToPostDTOList(allPosts);

        // Add the data (postList) to the model
        thePostsList.addAttribute("posts", postDTOS);

        //return the thymeleaf
        return "list-post";
    }
    
    
}
