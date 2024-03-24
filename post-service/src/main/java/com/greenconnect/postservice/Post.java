package com.greenconnect.postservice;

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
public class Post {

    @Id
    @GeneratedValue
    private Integer id;

    private String user_id;
    private String thread_id;
    private String text;
    private String test;
    //imag
}
