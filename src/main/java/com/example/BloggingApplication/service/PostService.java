package com.example.BloggingApplication.service;

import com.example.BloggingApplication.entities.Post;
import com.example.BloggingApplication.paylods.PostDto;
import com.example.BloggingApplication.paylods.PostResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface PostService {
//create

  public  PostDto creteatePost(PostDto postDto,Integer UserId,Integer CategoryId);

    //update
   public PostDto updatePost(PostDto postDto,Integer PostId);
    //delete
    void DeletePost(Integer  PostId);
    //getall
   public PostResponse getallPosts(Integer  pageSize, Integer pageNumber,String sortBy,String sortDir);
    //getById
  public  PostDto getPostById(Integer PostId);

  List<PostDto>getPostByCategory(Integer categoryId);

  List<PostDto> getPostByUser(Integer  UserId);

  List<PostDto> SearchKeyWord(String Keyword);

}
