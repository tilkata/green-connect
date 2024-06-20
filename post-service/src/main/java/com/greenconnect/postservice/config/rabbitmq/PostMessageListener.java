package com.greenconnect.postservice.config.rabbitmq;

import com.greenconnect.postservice.service.impl.PostServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PostMessageListener {

    @Autowired
    private PostServiceImpl postService;

    public void receiveMessage(Long userId) {
        postService.deleteAllPosts(userId);
    }
}
