package com.practice.blogappapi.services;

import com.practice.blogappapi.payloads.CommentDto;

public interface CommentService {

    CommentDto createComment(CommentDto commentDto,Integer postId);
    void deleteComment(Integer commentId);
}
