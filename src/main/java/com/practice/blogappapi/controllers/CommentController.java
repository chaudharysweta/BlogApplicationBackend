package com.practice.blogappapi.controllers;

import com.practice.blogappapi.entities.Comment;
import com.practice.blogappapi.payloads.ApiResponse;
import com.practice.blogappapi.payloads.CommentDto;
import com.practice.blogappapi.services.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class CommentController {

    @Autowired
    private CommentService commentService;

    @PostMapping("/posts/{postId}/comments")
    public ResponseEntity<CommentDto>createComment(@RequestBody CommentDto comment, @PathVariable Integer postId){

        CommentDto createComment = this.commentService.createComment(comment,postId);
        return new ResponseEntity<CommentDto>(createComment, HttpStatus.CREATED);
    }
    @DeleteMapping("/comments/{commentId}")
    public ResponseEntity<ApiResponse>deleteComment(@PathVariable Integer commentId){
        this.commentService.deleteComment(commentId);
        return new ResponseEntity<ApiResponse>(new ApiResponse("Comment deleted successfully",true),HttpStatus.OK);
    }

}
