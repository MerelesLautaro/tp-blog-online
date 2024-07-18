package com.lautadev.tp_blog_online.controller;

import com.lautadev.tp_blog_online.model.Post;
import com.lautadev.tp_blog_online.service.IPostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/post")
@PreAuthorize("denyAll()")
public class PostController {
    @Autowired
    private IPostService postService;

    @PostMapping("/save")
    @PreAuthorize("hasAnyRole('ADMIN','AUTHOR')")
    public ResponseEntity<String> savePost(@RequestBody Post post){
        postService.savePost(post);
        return ResponseEntity.ok("Posting created successfully");
    }

    @GetMapping("/get")
    @PreAuthorize("hasAnyRole('ADMIN','AUTHOR','USER')")
    public ResponseEntity<List<Post>> getPosts(){
        return ResponseEntity.ok(postService.getPosts());
    }

    @GetMapping("/get/{id}")
    @PreAuthorize("hasAnyRole('ADMIN','AUTHOR','USER')")
    public ResponseEntity<Post> findPost(@PathVariable Long id){
        Optional<Post> post = postService.findPost(id);
        return post.map(ResponseEntity::ok).orElseGet(()->ResponseEntity.notFound().build());
    }

    @DeleteMapping("/delete/{id}")
    @PreAuthorize("hasAnyRole('ADMIN','AUTHOR')")
    public ResponseEntity<String> deletePost(@PathVariable Long id){
        postService.deletePost(id);
        return ResponseEntity.ok("Post deleted successfully");
    }

    @PutMapping("/edit")
    @PreAuthorize("hasAnyRole('ADMIN','AUTHOR')")
    public ResponseEntity<Post> editPost(@RequestBody Post post){
        postService.editPost(post);
        Optional<Post> postEdit = postService.findPost(post.getId());
        return postEdit.map(ResponseEntity::ok).orElseGet(()->ResponseEntity.notFound().build());
    }
}
