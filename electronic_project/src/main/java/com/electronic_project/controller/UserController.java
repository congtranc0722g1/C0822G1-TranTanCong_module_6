package com.electronic_project.controller;

import com.electronic_project.dto.user.UserAvatarDto;
import com.electronic_project.dto.user.UserDto;
import com.electronic_project.model.user.User;
import com.electronic_project.service.IUserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@CrossOrigin("*")
@RequestMapping("/user")
public class UserController {

    @Autowired
    private IUserService userService;

    @PutMapping("/update")
    private ResponseEntity<?> updateUser(@RequestBody @Validated UserDto userDto, BindingResult bindingResult){
        if (bindingResult.hasErrors()){
            return new ResponseEntity<>(bindingResult.getAllErrors(), HttpStatus.BAD_REQUEST);
        }
        User user = new User();
        BeanUtils.copyProperties(userDto, user);
        userService.updateUser(user.getName(), user.getDateOfBirth(), user.getGender(), user.getPhone(), user.getEmail(), user.getAddress(), user.getId());
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/update-avatar")
    private ResponseEntity<?> updateAvatarUser(@RequestBody UserAvatarDto userAvatarDto){
        userService.updateAvatarUser(userAvatarDto.getAvatar(), userAvatarDto.getUserId());
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
