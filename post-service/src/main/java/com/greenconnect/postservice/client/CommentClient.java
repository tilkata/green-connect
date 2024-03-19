package com.greenconnect.postservice.client;

import com.greenconnect.postservice.Comment;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "comment-service", url = "${application.config.comments-url}")
public interface CommentClient {
    @GetMapping("/post/{post-id}")
    List<Comment> findAllCommentsByPost(@PathVariable("post-id") Integer postId);
}
