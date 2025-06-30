package com.example.BloggingApplication.service.impl;

import com.example.BloggingApplication.entities.Category;
import com.example.BloggingApplication.entities.Post;
import com.example.BloggingApplication.entities.User;
import com.example.BloggingApplication.exception.ResourceNotFoundException;
import com.example.BloggingApplication.paylods.PostDto;
import com.example.BloggingApplication.paylods.PostResponse;
import com.example.BloggingApplication.repository.CatagoryRepo;
import com.example.BloggingApplication.repository.PostRepo;
import com.example.BloggingApplication.repository.UserRepo;
import com.example.BloggingApplication.service.PostService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class postServiceImpl implements PostService {
    @Autowired
    private PostRepo postRepo;
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private UserRepo userRepo;
    @Autowired
    private CatagoryRepo catagoryRepo;

    @Override
    public PostDto creteatePost(PostDto postDto, Integer UserId, Integer CategoryId) {

        User user = this.userRepo.findById(UserId).orElseThrow(() ->
                new ResourceNotFoundException("User", "id", UserId));

        Category category = this.catagoryRepo.findById(CategoryId).orElseThrow(() ->
                new ResourceNotFoundException("Category", "id", CategoryId));

        Post post = this.modelMapper.map(postDto, Post.class);
        post.setImageName("default.png");
        post.setAddedDate(new Date());
        post.setUser(user);
        post.setCategory(category);

        Post newpost = this.postRepo.save(post);

        return this.PosttoDto(newpost);
    }

    @Override
    public PostDto updatePost(PostDto postDto, Integer PostId) {
        Post post = postRepo.findById(PostId).orElseThrow(() ->
                new ResourceNotFoundException("post", "postId", PostId));
        post.setTitle(postDto.getTitle());
        post.setContent(postDto.getContent());
        post.setImageName(postDto.getImageName());
        Post updatePost = postRepo.save(post);
        return this.modelMapper.map(updatePost, PostDto.class);
    }

    @Override
    public void DeletePost(Integer PostId) {
        Post post = postRepo.findById(PostId).orElseThrow(() ->
                new ResourceNotFoundException("post", "postId", PostId));
        postRepo.delete(post);

    }

    @Override
    public PostResponse getallPosts(Integer pageSize, Integer pageNumber, String sortBy, String sortDir) {


        Sort sort = null;
        if (sortDir.equalsIgnoreCase("asc")) {
            sort = Sort.by(sortBy).ascending();
        } else {
            sort = Sort.by(sortBy).descending();
        }

        Pageable p = PageRequest.of(pageNumber, pageSize, sort);
        Page<Post> pagePost = this.postRepo.findAll(p);

        List<Post> allposts = pagePost.getContent();


//        List<Post> allposts=postRepo.findAll();
        List<PostDto> postDtos = allposts.stream().map(e -> this.modelMapper.map(e, PostDto.class))
                .collect(Collectors.toList());


        PostResponse postResponse = new PostResponse();
        postResponse.setContent(postDtos);
        postResponse.setPageNumber(pagePost.getNumber());
        postResponse.setTotalElements(pagePost.getTotalElements());
        postResponse.setPageSize(pagePost.getSize());
        postResponse.setTotalPages(pagePost.getTotalPages());
        postResponse.setLastPage(pagePost.isLast());

        return postResponse;
    }

    @Override
    public PostDto getPostById(Integer PostId) {
        Post post = postRepo.findById(PostId).orElseThrow(() ->
                new ResourceNotFoundException("post", "postId", PostId));
        return this.modelMapper.map(post, PostDto.class);
    }

    @Override
    public List<PostDto> getPostByCategory(Integer categoryId) {
        Category cat = catagoryRepo.findById(categoryId)
                .orElseThrow(() -> new ResourceNotFoundException("Category", "id", categoryId));

        List<Post> posts = this.postRepo.findByCategory(cat); // ✅ corrected type
        return posts.stream()
                .map(post -> this.modelMapper.map(post, PostDto.class)) // ✅ mapping individual post
                .collect(Collectors.toList());
    }


    @Override

    public List<PostDto> getPostByUser(Integer userId) {
        User user = this.userRepo.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User", "id", userId));

        List<Post> posts = postRepo.findByUser(user); // ✅ corrected type
        return posts.stream()
                .map(post -> this.modelMapper.map(post, PostDto.class)) // ✅ mapping individual post
                .collect(Collectors.toList());
    }


    @Override
    public List<PostDto> SearchKeyWord(String Keyword) {
        return List.of();
    }

    public Post dtotoPost(PostDto postDto) {
        Post post = this.modelMapper.map(postDto, Post.class);
        return post;
    }

    public PostDto PosttoDto(Post post) {
        PostDto postDto = this.modelMapper.map(post, PostDto.class);
        return postDto;
    }
}
