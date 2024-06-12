package com.greenconnect.postservice.mapper;

import com.greenconnect.postservice.dto.PostDTO;
import com.greenconnect.postservice.model.Post;

public class PostMapper {
    public static PostDTO toPostDTO(Post post) {
        PostDTO dto = new PostDTO();
        dto.setAuthorId(post.getAuthorId());
        dto.setContentIdentifier(post.getContentIdentifier());
        dto.setTags(post.getTags());
        dto.setCategory(post.getCategory());
        dto.setVisibility(post.getVisibility());
        dto.setContentText(post.getContentText());
        return dto;
    }

    public static Post toPost(PostDTO postDTO) {
        Post post = new Post();
        post.setAuthorId(postDTO.getAuthorId());
        post.setContentIdentifier(postDTO.getContentIdentifier());
        post.setTags(postDTO.getTags());
        post.setCategory(postDTO.getCategory());
        post.setVisibility(postDTO.getVisibility());
        post.setContentText(postDTO.getContentText());
        return post;
    }
}
