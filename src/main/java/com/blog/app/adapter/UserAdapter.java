package com.blog.app.adapter;

import com.blog.app.db.entities.User;
import com.blog.app.request.UpdateUserRequestDto;
import com.blog.app.request.UserRequestDto;
import com.blog.app.response.UserResponseDto;

public class UserAdapter {

    public static User convertDtoToUserEntity(UserRequestDto userRequestDto) {

        User user = User.builder()
                .name(userRequestDto.getName())
                .userName(userRequestDto.getUserName())
                .email(userRequestDto.getEmail())
                .password(userRequestDto.getPassword())
                .about(userRequestDto.getAbout())
                .build();

        return user;
    }

    public static UserResponseDto convertUserEntityToUserResponseDto(User user) {
        UserResponseDto userResponseDto = UserResponseDto.builder()
                .id(user.getId())
                .name(user.getName())
                .userName(user.getUserName())
                .email(user.getEmail())
                .about(user.getAbout())
                .build();

        return userResponseDto;
    }

    public static void updateUserEntity(User user, UpdateUserRequestDto updateUserRequestDto) {

        if(updateUserRequestDto.getName() != null) {
            user.setName(updateUserRequestDto.getName());
        }
        if(updateUserRequestDto.getUserName() != null) {
            user.setUserName(updateUserRequestDto.getUserName());
        }
        if(updateUserRequestDto.getEmail() != null) {
            user.setEmail(updateUserRequestDto.getEmail());
        }
        if(updateUserRequestDto.getAbout() != null) {
            user.setAbout(updateUserRequestDto.getAbout());
        }

    }
}
