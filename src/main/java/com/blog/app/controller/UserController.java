package com.blog.app.controller;

import com.blog.app.request.UpdateUserRequestDto;
import com.blog.app.request.UserRequestDto;
import com.blog.app.response.BaseResponse;
import com.blog.app.response.UserResponseDto;
import com.blog.app.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@Tag(name = "User API", description = "Create and Fetch User Details")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/v1/user")
    @Operation(description = "This API is used to add user details")
    public ResponseEntity<BaseResponse> create(@RequestBody @Valid UserRequestDto userRequestDto) {
        UserResponseDto createdUser = this.userService.createUser(userRequestDto);
        BaseResponse response = new BaseResponse("SUCCESS", "User Created Successfully", createdUser);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PutMapping("/{userId}")
    @Operation(description = "This API is used to update user details by userId")
    public ResponseEntity<BaseResponse> updateUser(@PathVariable("userId") Integer userId, @RequestBody UpdateUserRequestDto updateUserRequestDto) {
        BaseResponse response  = this.userService.updateUser(userId, updateUserRequestDto);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping("/v1/user/{userId}")
    @Operation(description = "This API is used to fetch user details by userId")
    public ResponseEntity<BaseResponse> getUsers(@PathVariable("userId") Integer userId) {
        UserResponseDto fetchedUser = this.userService.getUserById(userId);
        BaseResponse response = new BaseResponse("SUCCESS", "User Fetched Successfully", fetchedUser);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping("/v1/user")
    @Operation(description = "This API is used to fetch all users")
    public ResponseEntity<BaseResponse> getAllUsers() {
        List<UserResponseDto> users = this.userService.getAllUsers();
        BaseResponse response = new BaseResponse("SUCCESS", "User Fetched Successfully", users);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @DeleteMapping("/v1/user/{userId}")
    @Operation(description = "This API is used to delete user by userId")
    public ResponseEntity<BaseResponse> deleteUser(@PathVariable("userId") Integer userId) {
        this.userService.deleteUser(userId);
        BaseResponse response = new BaseResponse("SUCCESS", "User Deleted Successfully", "");
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}
