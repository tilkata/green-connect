package com.greenconnect.commentservice;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentService {
    private final CommentRepository repository;

    public void saveComment(Comment comment){
        repository.save(comment);
    }
    public List<Comment> findAllComments(){
        return repository.findAll();
    }

    public List<Comment> findAllCommentsByPost(Integer postId) {
        return repository.findAllByPostId(postId);
    }
}
