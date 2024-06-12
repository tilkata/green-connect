package com.greenconnect.commentservice.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CommentDTO {
    private Long postId;
    private Long authorId;
    private String content;
    private Long parentId;
}
