package com.practice.blogappapi.repositories;

import com.practice.blogappapi.entities.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepo extends JpaRepository<Category,Integer> {

}
