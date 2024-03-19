package com.greenconnect.postservice;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Comment {

    private String user_id;
    private String text;
}
