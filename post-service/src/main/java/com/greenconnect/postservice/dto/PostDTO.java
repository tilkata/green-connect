package com.greenconnect.postservice.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;
import com.greenconnect.postservice.model.Visibility;
import java.util.Set;


@Getter
@Setter
public class PostDTO {
    private Long authorId;
    private String contentIdentifier;
    private String contentText;
    private Set<String> tags;
    private String category;
    private Visibility visibility;
    private String contentType;
}
