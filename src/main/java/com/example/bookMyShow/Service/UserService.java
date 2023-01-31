package com.example.bookMyShow.Service;

import com.example.bookMyShow.Model.UserEntity;
import com.example.bookMyShow.Repository.UserRepository;
import com.example.bookMyShow.dto.UserRequestDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;


    public  String addUser(UserRequestDto userRequestDto){
        UserEntity userEntity = UserEntity.builder()
                .mobile(userRequestDto.getMobile())
                .name(userRequestDto.getName()).build();
        try{
            userRepository.save(userEntity);
        }catch (Exception e){
            return "user could not be added";
        }

        return "User added successfully";
    }
}
