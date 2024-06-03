package com.greenconnect.postservice.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;
import com.greenconnect.postservice.model.Visibility;
import java.util.Set;

@Getter
@Setter
public class PostDTO {
    private String content;
    private Set<String> tags;
    private String category;
    private Visibility visibility;
    // "text", "image", or "video"
    private String contentType;
    // For image and video content
    private MultipartFile file;

}
