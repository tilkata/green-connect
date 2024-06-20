package com.greenconnect.commentservice.config.rabbitmq;

import com.greenconnect.commentservice.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CommentMessageListener {

    @Autowired
    private CommentService commentService;

    public void receiveMessage(Long postId) {
        commentService.deleteCommentsByPostId(postId);
    }
    public void receiveUserDeleteMessage(Long userId) {
        commentService.deleteCommentsByAuthorId(userId);
    }
}
