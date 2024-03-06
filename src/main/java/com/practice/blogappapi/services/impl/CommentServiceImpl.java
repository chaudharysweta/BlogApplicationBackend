package com.practice.blogappapi.services.impl;

import com.practice.blogappapi.entities.Comment;
import com.practice.blogappapi.entities.Post;
import com.practice.blogappapi.exceptions.ResourceNotFoundException;
import com.practice.blogappapi.payloads.CommentDto;
import com.practice.blogappapi.repositories.CommentRepo;
import com.practice.blogappapi.repositories.PostRepo;
import com.practice.blogappapi.services.CommentService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    private PostRepo postRepo;
    @Autowired
    private CommentRepo commentRepo;
    @Autowired
    private ModelMapper modelMapper;
    @Override
    public CommentDto createComment(CommentDto commentDto, Integer postId) {

        Post post=this.postRepo.findById(postId).orElseThrow(()->new ResourceNotFoundException("Post","post id",postId));
        Comment comment= this.modelMapper.map(commentDto, Comment.class);
        comment.setPost(post);
        Comment savedComment=this.commentRepo.save(comment);
        return this.modelMapper.map(savedComment, CommentDto.class);
    }

    @Override
    public void deleteComment(Integer commentId) {
        Comment com=this.commentRepo.findById(commentId).orElseThrow(()->new ResourceNotFoundException("Comment","comment id",commentId));
        this.commentRepo.delete(com);

    }
}
