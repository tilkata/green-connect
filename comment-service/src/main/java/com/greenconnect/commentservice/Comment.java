package com.greenconnect.commentservice;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Comment {

    @Id
    @GeneratedValue
    private Integer id;

    private String user_id;
    private String thread_id;
    private Integer postId;
    private String text;
}
