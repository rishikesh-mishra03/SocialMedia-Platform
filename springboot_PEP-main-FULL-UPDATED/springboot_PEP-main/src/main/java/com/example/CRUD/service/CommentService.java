package com.example.CRUD.service;


import com.example.CRUD.entity.Comment;
import com.example.CRUD.entity.Post;
import com.example.CRUD.entity.User;
import com.example.CRUD.repository.CommentRepository;
import com.example.CRUD.repository.PostRepository;
import com.example.CRUD.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;
    private final UserRepository userRepository;
    private final PostRepository postRepository;


    @Transactional
    public Comment addComment(Long userId, Long postId, String content) {

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new RuntimeException("Post not found"));

        Comment comment = new Comment();
        comment.setComment(content);
        comment.setUser(user);
        comment.setPost(post);

        return commentRepository.save(comment);
    }

    @Transactional
    public Comment updateComment(Long commentId, Long userId, String content) {

        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new RuntimeException("Comment not found"));



        comment.setComment(content);
        return comment;
    }

    @Transactional
    public void deleteComment(Long commentId, Long userId, boolean isAdmin) {

        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new RuntimeException("Comment not found"));



        commentRepository.delete(comment);
    }

    public List<Comment> getCommentsByPost(Long postId) {

        return commentRepository.findByPostIdOrderByCreatedAtDesc(postId);
    }

    public long countComments(Long postId) {
        return commentRepository.countByPostId(postId);
    }
}
