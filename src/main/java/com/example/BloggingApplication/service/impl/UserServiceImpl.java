package com.example.BloggingApplication.service.impl;

import com.example.BloggingApplication.entities.User;
import com.example.BloggingApplication.exception.ResourceNotFoundException;
import com.example.BloggingApplication.paylods.UserDto;
import com.example.BloggingApplication.repository.UserRepo;
import com.example.BloggingApplication.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
public class UserServiceImpl  implements UserService {
    @Autowired
  private  UserRepo userRepo;
    @Autowired

    private ModelMapper modelMapper;

    @Override
    public UserDto createUser(UserDto dto) {
User user=userRepo.save(this.dtotoUser(dto));
return this.UsertoDto(user);

    }

    @Override
    public UserDto updateUser(UserDto user, Integer userId) {
      return  userRepo.findById(userId).map(e->{
            e.setId(user.getId());
            e.setName(user.getName());
            e.setEmail(user.getEmail());
            e.setPassword(user.getPassword());
            e.setAbout(user.getAbout());
            return this.UsertoDto(userRepo.save(e));
        }).orElseThrow(()-> new ResourceNotFoundException("User","id", userId));
    }

    @Override
    public UserDto getUserById(Integer userId) {
       User user = userRepo.findById((userId)).orElseThrow(()->new ResourceNotFoundException("User", "id", userId));
       return this.UsertoDto(user);

    }

    @Override
    public List<UserDto> getAllUsers() {
   return  userRepo.findAll()
           .stream().
           map(this::UsertoDto)
           .collect(Collectors.toList());
    }

    @Override
    public Void DeleteUser(Integer userId) {
      User user=  userRepo.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User", "id", userId));
        userRepo.delete(user);
return null;
    }

    private User dtotoUser(UserDto userDto){

        User user=this.modelMapper.map(userDto,User.class);
     // User user = new User();
//        user.setId(userDto.getId());
//        user.setName(userDto.getName());
//        user.setEmail(userDto.getEmail());
//        user.setPassword(userDto.getPassword());
//        user.setAbout(userDto.getAbout());
    return user;
    }

    private UserDto UsertoDto(User user){

        UserDto userDto=this.modelMapper.map(user,UserDto.class);
//        UserDto userDto = new UserDto();
//        userDto.setId(user.getId());
//        userDto.setName(user.getName());
//        userDto.setEmail(user.getEmail());
//        userDto.setPassword(user.getPassword());
//        userDto.setAbout(user.getAbout());
        return userDto;
    }




}
