package com.example.CRUD.controller;

import com.example.CRUD.entity.Comment;
import com.example.CRUD.service.CommentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/comments")
public class CommentController {

    private final CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @PostMapping
    public ResponseEntity<Comment> addComment(
            @RequestParam Long userId,
            @RequestParam Long postId,
            @RequestBody String content) {

        Comment comment = commentService.addComment(userId, postId, content);
        return ResponseEntity.ok(comment);
    }

    @PutMapping("/{commentId}")
    public ResponseEntity<Comment> updateComment(
            @PathVariable Long commentId,
            @RequestParam Long userId,
            @RequestBody String content) {

        Comment updated = commentService.updateComment(commentId, userId, content);
        return ResponseEntity.ok(updated);
    }


    @DeleteMapping("/{commentId}")
    public ResponseEntity<String> deleteComment(
            @PathVariable Long commentId,
            @RequestParam Long userId,
            @RequestParam(defaultValue = "false") boolean isAdmin) {

        commentService.deleteComment(commentId, userId, isAdmin);
        return ResponseEntity.ok("Comment deleted successfully");
    }


    @GetMapping("/post/{postId}")
    public ResponseEntity<List<Comment>> getCommentsByPost(
            @PathVariable Long postId) {

        List<Comment> comments = commentService.getCommentsByPost(postId);
        return ResponseEntity.ok(comments);
    }


    @GetMapping("/count/{postId}")
    public ResponseEntity<Long> countComments(
            @PathVariable Long postId) {

        long count = commentService.countComments(postId);
        return ResponseEntity.ok(count);
    }
}
