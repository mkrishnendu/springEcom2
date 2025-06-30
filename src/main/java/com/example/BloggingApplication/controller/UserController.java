package com.example.BloggingApplication.controller;


import com.example.BloggingApplication.paylods.ApiResponse;
import com.example.BloggingApplication.paylods.UserDto;
import com.example.BloggingApplication.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/users")
public class UserController {
@Autowired
    private UserService userService;

@PostMapping("/add-user")
public ResponseEntity<UserDto> createUser(@Valid @RequestBody UserDto userDto){
    UserDto createUser=userService.createUser(userDto);
    return new ResponseEntity<>(createUser, HttpStatus.CREATED);
}
@PutMapping ("/update-user/{id}")
public ResponseEntity<UserDto>updateUser( @Valid @RequestBody UserDto userDto, @PathVariable Integer id){
    UserDto updateUser=userService.updateUser(userDto,id);
    return new ResponseEntity<>(updateUser,HttpStatus.OK);
}
@DeleteMapping("/{id}")
public ResponseEntity<ApiResponse> DeleteUser(@PathVariable int id){
    userService.DeleteUser(id);
    return new ResponseEntity<ApiResponse>(new ApiResponse("user deleted sucessfully",true),HttpStatus.OK);
}
@GetMapping("/get-all-user")
public ResponseEntity<List<UserDto>>getAllUser(){

     return new ResponseEntity<>(userService.getAllUsers(),HttpStatus.OK);
}
@GetMapping("/{id}")
public ResponseEntity<UserDto>getsingleuser(@PathVariable int id){
    return new ResponseEntity<>(userService.getUserById(id),HttpStatus.OK);
}







}
