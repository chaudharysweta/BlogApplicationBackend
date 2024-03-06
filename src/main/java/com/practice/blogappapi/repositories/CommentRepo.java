package com.practice.blogappapi.repositories;

import com.practice.blogappapi.entities.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepo extends JpaRepository<Comment,Integer> {
}
