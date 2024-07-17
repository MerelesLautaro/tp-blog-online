package com.lautadev.tp_blog_online.service;

import com.lautadev.tp_blog_online.model.Post;

import java.util.List;
import java.util.Optional;

public interface IPostService {
    public void savePost(Post post);
    public List<Post> getPosts();
    public Optional<Post> findPost(Long id);
    public void deletePost(Long id);
    public void editPost(Post post);
}
