package finalProject.domain;

import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Customer {
    private Long id;
    private String username;
    private List<Post> posts;
    private List<Comment> comments;
}
