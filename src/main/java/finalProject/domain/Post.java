package finalProject.domain;

import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Post {
    private Long id;
    private Customer customer;
    private String content;
    private LocalDateTime createdAt;
    private List<Comment> comments;
}
