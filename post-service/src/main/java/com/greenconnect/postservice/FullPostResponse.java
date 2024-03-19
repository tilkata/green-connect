package com.greenconnect.postservice;

import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FullPostResponse {
    private String text;
    List<Comment> comment;
}
