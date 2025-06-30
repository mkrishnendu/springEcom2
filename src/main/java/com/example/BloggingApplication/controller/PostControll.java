package com.example.BloggingApplication.controller;


import com.example.BloggingApplication.entities.Post;
import com.example.BloggingApplication.paylods.ApiResponse;
import com.example.BloggingApplication.paylods.PostDto;
import com.example.BloggingApplication.paylods.PostResponse;
import com.example.BloggingApplication.service.PostService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class PostControll {
    @Autowired
 private PostService postService;


    //create
    @PostMapping("/user/{userId}/category/{categoryId}/posts")
    public ResponseEntity<PostDto>CreatePosts(@RequestBody PostDto postDto,
                                              @PathVariable Integer userId,
                                              @PathVariable Integer categoryId){

        PostDto postDto1=postService.creteatePost(postDto,userId,categoryId);
        return new ResponseEntity<PostDto>(postDto1, HttpStatus.CREATED);

    }


@GetMapping("/user/{userId}/posts")
    public ResponseEntity<List<PostDto>>getPostByUser(@PathVariable Integer userId){
  List<PostDto>  posts=postService.getPostByUser(userId);
  return new ResponseEntity<List<PostDto>>(posts,HttpStatus.OK);

}


    @GetMapping("/category/{categoryId}/posts")
    public ResponseEntity<List<PostDto>>getPostByCategory(@PathVariable Integer categoryId){
        List<PostDto>  posts=postService.getPostByCategory(categoryId);
        return new ResponseEntity<List<PostDto>>(posts,HttpStatus.OK);

    }
    //getallposts
    @GetMapping("/posts")
    public ResponseEntity<PostResponse> getAllPosts(
            @RequestParam(value = "pageNumber", defaultValue = "0", required = false) Integer pageNumber,
            @RequestParam(value = "pageSize", defaultValue = "10", required = false) Integer pageSize,
            @RequestParam(value = "sortBy", defaultValue = "postId", required = false) String sortBy,
            @RequestParam(value = "sortDir", defaultValue = "asc", required = false) String sortDir
    ) {
        PostResponse postResponse = postService.getallPosts(pageSize, pageNumber, sortBy, sortDir);
        return new ResponseEntity<>(postResponse, HttpStatus.OK);
    }

    @GetMapping("/posts/{PostId}")
    public ResponseEntity<PostDto> getPostById(@PathVariable Integer PostId){
      PostDto postDto=  postService.getPostById(PostId);
        return new ResponseEntity<>(postDto,HttpStatus.OK);
    }

    @DeleteMapping("/posts/{postId}")
    public ApiResponse deletePostDto(@PathVariable Integer postId){
        postService.DeletePost(postId);
        return new ApiResponse("post is deleted",true);
    }

    @PutMapping("/posts/{postId}")
    public ResponseEntity<PostDto> updatePostDto(@Valid @RequestBody PostDto postDto, @PathVariable Integer postId){
        PostDto postDto1=postService.updatePost(postDto,postId);
        return new ResponseEntity<PostDto>(postDto1,HttpStatus.OK);
    }
}
