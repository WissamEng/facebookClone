package finalProject.dto;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CommentDTO {
    private Long id;
    private Long customerId;
    private Long postId;
    private String content;
    private LocalDateTime createdAt;
}
