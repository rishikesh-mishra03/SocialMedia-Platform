package com.example.CRUD.controller;


import org.springframework.web.bind.annotation.RestController;


import com.example.CRUD.service.LikeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/like")
public class LikeController {

    @Autowired
    private LikeService likeService;

    @PostMapping("/toggle")
    public ResponseEntity<String> toggleLike(
            @RequestParam Long userId,
            @RequestParam Long postId) {

        String response = likeService.toggleLike(userId, postId);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/count/{postId}")
    public ResponseEntity<Long> getLikeCount(@PathVariable Long postId) {

        long count = likeService.getLikeCount(postId);
        return ResponseEntity.ok(count);
    }


    @GetMapping("/is-liked")
    public ResponseEntity<Boolean> isLiked(
            @RequestParam Long userId,
            @RequestParam Long postId) {

        boolean liked = likeService.isLiked(userId, postId);
        return ResponseEntity.ok(liked);
    }
}
