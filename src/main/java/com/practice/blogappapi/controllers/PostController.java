package com.practice.blogappapi.controllers;

import com.practice.blogappapi.config.AppConstants;
import com.practice.blogappapi.payloads.ApiResponse;
import com.practice.blogappapi.payloads.PostDto;
import com.practice.blogappapi.payloads.PostResponse;
import com.practice.blogappapi.services.FileSerivce;
import com.practice.blogappapi.services.PostService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@RestController
@RequestMapping("/api")
public class PostController {

    //    @Autowired
//    private PostService postService;
//

    private final FileSerivce fileSerivce;
    private final  PostService postService;


    public PostController(FileSerivce fileSerivce, PostService postService) {
        this.fileSerivce = fileSerivce;
        this.postService = postService;
    }
    @Value("${project.image}")
    private String path;


    //create
    @PostMapping("/user/{userId}/category/{categoryId}/posts")
    public ResponseEntity<PostDto> createPost(@RequestBody PostDto postDto, @PathVariable Integer userId,@PathVariable Integer categoryId){
        PostDto createPost = this.postService.createPost(postDto,userId,categoryId);
        return new ResponseEntity<PostDto>(createPost,HttpStatus.CREATED);
    }
    //get by user
    @GetMapping("/user/{userId}/posts")
    public ResponseEntity<List<PostDto>> getPostsByUser(@PathVariable Integer userId){
        List<PostDto> posts = this.postService.getPostsByUser(userId);
        return new ResponseEntity<List<PostDto>>(posts,HttpStatus.OK);
    }
    @GetMapping("/category/{categoryId}/posts")
    public ResponseEntity<List<PostDto>> getPostsByCategory(@PathVariable Integer categoryId){
        List<PostDto> posts = this.postService.getPostsByCategory(categoryId);
        return new ResponseEntity<List<PostDto>>(posts,HttpStatus.OK);
    }
    //get all post
    @GetMapping("/posts")
    public ResponseEntity<PostResponse> getAllPost(@RequestParam(value = "pageNumber",defaultValue = AppConstants.PAGE_NUMBER,required = false)Integer pageNumber,
                                                   @RequestParam(value = "pageSize",defaultValue = AppConstants.PAGE_SIZE,required = false)Integer pageSize,
                                                   @RequestParam(value = "sortBy",defaultValue = AppConstants.SORT_BY,required = false)String sortBy,
                                                   @RequestParam(value = "sortDir",defaultValue = AppConstants.SORT_DIR,required = false)String sortDir){
        PostResponse postResponse = this.postService.getAllPost(pageNumber,pageSize,sortBy,sortDir);
        return new ResponseEntity<PostResponse>(postResponse,HttpStatus.OK);
    }
    //get post by id
    @GetMapping("/posts/{postId}")
    public ResponseEntity<PostDto> getPostById(@PathVariable Integer postId){
       PostDto postDto=this.postService.getPostById(postId);
        return new ResponseEntity<PostDto>(postDto,HttpStatus.OK);
    }
    //delete post
    @DeleteMapping("/posts/{postId}")
    public ApiResponse deletePost(@PathVariable Integer postId){
        this.postService.deletePost(postId);
        return new ApiResponse("Post is deleted successfully...",true);
    }
    @PutMapping("/posts/{postId}")
    public ResponseEntity<PostDto> updatePost(@RequestBody PostDto postDto,@PathVariable Integer postId){
        PostDto updatePost = this.postService.updtePost(postDto,postId);
        return new ResponseEntity<PostDto>(updatePost,HttpStatus.OK);
    }

    //post image upload
    @PostMapping("/posts/image/upload/{postId}")
    public ResponseEntity<PostDto> uploadPostImage(
            @RequestParam("image")MultipartFile image,
            @PathVariable Integer postId) throws IOException {
        PostDto postDto=this.postService.getPostById(postId);
        String fileName=this.fileSerivce.uploadImage(path,image);

        postDto.setImageName(fileName);
        PostDto updatePost = this.postService.updtePost(postDto,postId);
        return new ResponseEntity<PostDto>(updatePost,HttpStatus.OK);
    }

    //methods to serve files
    @GetMapping(value = "/posts/image/{imageName}",produces = MediaType.IMAGE_JPEG_VALUE)
    public void downloadImage(
            @PathVariable("imageName") String imageName, HttpServletResponse response)throws  IOException{
        InputStream resorce=this.fileSerivce.getResource(path,imageName);
        response.setContentType(MediaType.IMAGE_JPEG_VALUE);
        StreamUtils.copy(resorce,response.getOutputStream());
    }


}
