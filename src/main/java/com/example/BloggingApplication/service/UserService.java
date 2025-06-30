package com.example.BloggingApplication.service;

import com.example.BloggingApplication.entities.User;
import com.example.BloggingApplication.paylods.UserDto;
import jakarta.persistence.criteria.CriteriaBuilder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService {



    UserDto  createUser(UserDto dto);

 UserDto updateUser(UserDto user,Integer userId);

  UserDto   getUserById(Integer userId);

  List<UserDto> getAllUsers();

  Void DeleteUser(Integer userId);

}
