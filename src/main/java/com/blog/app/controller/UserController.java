package com.blog.app.controller;

import com.blog.app.request.UpdateUserRequestDto;
import com.blog.app.request.UserRequestDto;
import com.blog.app.response.BaseResponse;
import com.blog.app.response.UserResponseDto;
import com.blog.app.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/")
    public ResponseEntity<BaseResponse> create(@RequestBody UserRequestDto userRequestDto) {
        UserResponseDto createdUser = this.userService.createUser(userRequestDto);
        BaseResponse response = new BaseResponse("SUCCESS", "User Created Successfully", createdUser);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PutMapping("/{userId}")
    public ResponseEntity<BaseResponse> updateUser(@PathVariable("userId") Integer userId, @RequestBody UpdateUserRequestDto updateUserRequestDto) {
        BaseResponse response  = this.userService.updateUser(userId, updateUserRequestDto);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<BaseResponse> getUsers(@PathVariable("userId") Integer userId) {
        UserResponseDto fetchedUser = this.userService.getUserById(userId);
        BaseResponse response = new BaseResponse("SUCCESS", "User Fetched Successfully", fetchedUser);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping("/")
    public ResponseEntity<BaseResponse> getAllUsers() {
        List<UserResponseDto> users = this.userService.getAllUsers();
        BaseResponse response = new BaseResponse("SUCCESS", "User Fetched Successfully", users);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<BaseResponse> deleteUser(@PathVariable("userId") Integer userId) {
        this.userService.deleteUser(userId);
        BaseResponse response = new BaseResponse("SUCCESS", "User Deleted Successfully", "");
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(response);
    }
}
