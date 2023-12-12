package finalProject.domain;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Comment {
    private Long id;
    private Customer customer;
    private Post post;
    private String content;
    private LocalDateTime createdAt;
}
