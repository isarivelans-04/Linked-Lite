package com.linkedlite.backend.serviceimpl;

import com.linkedlite.backend.entity.Post;
import com.linkedlite.backend.entity.User;
import com.linkedlite.backend.exception.UnauthorizedException;
import com.linkedlite.backend.repository.PostRepository;
import com.linkedlite.backend.repository.UserRepository;
import com.linkedlite.backend.service.PostService;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.UUID;

@Service
public class PostServiceImpl implements PostService {

    private final PostRepository postRepository;
    private final UserRepository userRepository;

    public PostServiceImpl(
            PostRepository postRepository,
            UserRepository userRepository) {

        this.postRepository = postRepository;
        this.userRepository = userRepository;
    }

    @Override
    public Post addPost(
            Post post,
            MultipartFile image,
            String loggedInEmail) {

        User user = userRepository.findByEmail(loggedInEmail)
                .orElseThrow(() ->
                        new RuntimeException("User not found"));

        // Set logged-in user's ID automatically
        post.setUserId(user.getUserId());
        // If no content was provided, save empty text
        if (post.getContent() == null) {
            post.setContent("");
        }

        // Save image if user selected one
        if (image != null && !image.isEmpty()) {

            try {

                // Create uploads/posts folder
                Path uploadDirectory =
                        Paths.get("uploads/posts");

                Files.createDirectories(uploadDirectory);

                // Create unique file name
                String fileName =
                        UUID.randomUUID() + "_" + image.getOriginalFilename();

                // Complete file path
                Path filePath =
                        uploadDirectory.resolve(fileName);

                // Save image
                Files.copy(
                        image.getInputStream(),
                        filePath,
                        StandardCopyOption.REPLACE_EXISTING
                );

                // Save image URL in database
                post.setImageUrl(
                        "/uploads/posts/" + fileName
                );

            } catch (IOException e) {

                throw new RuntimeException(
                        "Failed to save image"
                );
            }
        }

        return postRepository.save(post);
    }

    @Override
    public List<Post> getAllPosts() {
        return postRepository.findAll();
    }

    @Override
    public List<Post> getPostsByUserId(Integer userId) {
        return postRepository.findByUserId(userId);
    }

    @Override
    public Post getPostById(Integer postId) {

        return postRepository.findById(postId)
                .orElseThrow(() ->
                        new RuntimeException("Post not found"));
    }

    @Override
    public Post updatePost(
            Integer postId,
            Post updatedPost,
            String loggedInEmail) {

        Post existingPost = postRepository.findById(postId)
                .orElseThrow(() ->
                        new RuntimeException("Post not found"));

        User user = userRepository.findByEmail(loggedInEmail)
                .orElseThrow(() ->
                        new RuntimeException("User not found"));

        if (!user.getUserId().equals(existingPost.getUserId())) {

            throw new UnauthorizedException(
                    "You can update only your own post"
            );
        }

        existingPost.setContent(
                updatedPost.getContent()
        );

        existingPost.setImageUrl(
                updatedPost.getImageUrl()
        );

        return postRepository.save(existingPost);
    }

    @Override
    public void deletePost(
            Integer postId,
            String loggedInEmail) {

        Post existingPost = postRepository.findById(postId)
                .orElseThrow(() ->
                        new RuntimeException("Post not found"));

        User user = userRepository.findByEmail(loggedInEmail)
                .orElseThrow(() ->
                        new RuntimeException("User not found"));

        if (!user.getUserId().equals(existingPost.getUserId())) {

            throw new UnauthorizedException(
                    "You can delete only your own post"
            );
        }

        postRepository.deleteById(postId);
    }
}