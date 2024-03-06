package com.practice.blogappapi.services;

import com.practice.blogappapi.entities.Post;
import com.practice.blogappapi.payloads.PostDto;
import com.practice.blogappapi.payloads.PostResponse;

import java.util.List;

public interface PostService {

    //create
    PostDto createPost(PostDto postDto,Integer userId,Integer categoryId);

    //update
    PostDto updtePost(PostDto postDto,Integer postId);

    //delete
    void deletePost(Integer postId);

    //get all posts
    PostResponse getAllPost(Integer pageNumber, Integer pageSize,String sortBy,String sortDir);

    //get single post
    PostDto getPostById(Integer postId);

    //get all posts by category
    List<PostDto>getPostsByCategory(Integer categoryId);

    //get all posts by user
    List<PostDto>getPostsByUser(Integer userId);

    //search posts
    List<Post> searchPosts(String keyword);
}
