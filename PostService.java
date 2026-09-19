package com.linkedlite.backend.service;

import com.linkedlite.backend.entity.Post;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface PostService {

    Post addPost(
            Post post,
            MultipartFile image,
            String loggedInEmail
    );
    List<Post> getAllPosts();
    List<Post> getPostsByUserId(Integer userId);
    Post getPostById(Integer postId);
    Post updatePost(Integer postId, Post updatedPost, String loggedInEmail);
    void deletePost(Integer postId, String loggedInEmail);
}
