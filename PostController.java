package com.linkedlite.backend.controller;

import com.linkedlite.backend.entity.Post;
import com.linkedlite.backend.service.PostService;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api/posts")
public class PostController {

    private final PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    @PostMapping(consumes = "multipart/form-data")
    public Post addPost(
            @RequestParam(required = false) String content,
            @RequestPart(required = false) MultipartFile image) {

        String loggedInEmail =
                SecurityContextHolder
                        .getContext()
                        .getAuthentication()
                        .getName();

        Post post = new Post();

        if (content == null) {
            post.setContent("");
        } else {
            post.setContent(content);
        }

        return postService.addPost(
                post,
                image,
                loggedInEmail
        );
    }

    @GetMapping
    public List<Post> getAllPosts() {
        return postService.getAllPosts();
    }

    @GetMapping("/user/{userId}")
    public List<Post> getPostsByUserId(
            @PathVariable Integer userId) {

        return postService.getPostsByUserId(userId);
    }

    @GetMapping("/{postId}")
    public Post getPostById(
            @PathVariable Integer postId) {

        return postService.getPostById(postId);
    }

    @PutMapping("/{postId}")
    public Post updatePost(
            @PathVariable Integer postId,
            @RequestBody Post post) {

        String loggedInEmail =
                SecurityContextHolder
                        .getContext()
                        .getAuthentication()
                        .getName();

        return postService.updatePost(
                postId,
                post,
                loggedInEmail
        );
    }

    @DeleteMapping("/{postId}")
    public String deletePost(
            @PathVariable Integer postId) {

        String loggedInEmail =
                SecurityContextHolder
                        .getContext()
                        .getAuthentication()
                        .getName();

        postService.deletePost(
                postId,
                loggedInEmail
        );

        return "Post deleted successfully";
    }
}