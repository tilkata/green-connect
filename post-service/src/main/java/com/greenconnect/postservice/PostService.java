package com.greenconnect.postservice;

import com.greenconnect.postservice.client.CommentClient;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PostService {
    private final PostRepository repository;
    private final CommentClient client;

    public void savePost(Post post){
        repository.save(post);
    }
    public List<Post> findAllPosts(){
        return repository.findAll();
    }

    public void deletePost(Integer post_id){
        repository.deleteById(post_id);
    }

    public  void updatePost(Post post){
        Post newPost = repository.findById(post.getId())
                .orElseThrow(() -> new EntityNotFoundException("Post not found with id " + post.getId()));

        newPost.setText(post.getText());

        repository.save(newPost);
    }

    public FullPostResponse findPostsWithComments(Integer postId) {

        var post = repository.findById(postId)
                .orElse(Post.builder()
                        .text("NOT_FOUND")
                        .build()
                );
        var comments = client.findAllCommentsByPost(postId);
        return FullPostResponse.builder()
                .text(post.getText())
                .comment(comments)
                .build();
    }
}
