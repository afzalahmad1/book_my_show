package com.example.bookMyShow.Controller;

import com.example.bookMyShow.Service.UserService;
import com.example.bookMyShow.dto.UserRequestDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping("/add")
    public String addUser(@RequestBody UserRequestDto userRequestDto){
       return userService.addUser(userRequestDto);
    }

    //public String deleteUserById(@RequestParam )
}
