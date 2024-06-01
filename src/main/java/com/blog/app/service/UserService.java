package com.blog.app.service;


import com.blog.app.request.UpdateUserRequestDto;
import com.blog.app.request.UserRequestDto;
import com.blog.app.response.BaseResponse;
import com.blog.app.response.UserResponseDto;

import java.util.List;

public interface UserService {

    UserResponseDto createUser(UserRequestDto userRequestDto);
    BaseResponse updateUser(Integer userId, UpdateUserRequestDto updateUserRequestDto);
    UserResponseDto getUserById(Integer userId);
    List<UserResponseDto> getAllUsers();
    void deleteUser(Integer userId);
}
