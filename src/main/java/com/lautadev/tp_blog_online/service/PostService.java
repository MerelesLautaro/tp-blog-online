package com.lautadev.tp_blog_online.service;

import com.lautadev.tp_blog_online.model.Post;
import com.lautadev.tp_blog_online.repository.IPostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PostService implements IPostService{
    @Autowired
    private IPostRepository postRepository;

    @Override
    public void savePost(Post post) {
        postRepository.save(post);
    }

    @Override
    public List<Post> getPosts() {
        return postRepository.findAll();
    }

    @Override
    public Optional<Post> findPost(Long id) {
        return postRepository.findById(id);
    }

    @Override
    public void deletePost(Long id) {
        postRepository.deleteById(id);
    }

    @Override
    public void editPost(Post post) {
        this.savePost(post);
    }
}
