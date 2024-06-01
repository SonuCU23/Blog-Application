package com.blog.app.service.impl;

import com.blog.app.adapter.UserAdapter;
import com.blog.app.db.entities.User;
import com.blog.app.db.repository.UserRepository;
import com.blog.app.exceptions.ResourceNotFoundException;
import com.blog.app.request.UpdateUserRequestDto;
import com.blog.app.request.UserRequestDto;
import com.blog.app.response.BaseResponse;
import com.blog.app.response.UserResponseDto;
import com.blog.app.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    private Logger log = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    private UserRepository userRepository;


    @Override
    public UserResponseDto createUser(UserRequestDto userRequestDto) {
        User userEntity = UserAdapter.convertDtoToUserEntity(userRequestDto);
        this.userRepository.save(userEntity);
        return UserAdapter.convertUserEntityToUserResponseDto(userEntity);
    }

    @Override
    public BaseResponse updateUser(Integer userId, UpdateUserRequestDto updateUserRequestDto) {
        Optional<User> user = this.userRepository.findById(userId);
        if (!user.isPresent()) {
            log.error("No User found for UserId: {}", userId);
            throw new ResourceNotFoundException("user", "id", userId);
        }

        User userEntity = user.get();
        UserAdapter.updateUserEntity(userEntity, updateUserRequestDto);
        this.userRepository.save(userEntity);

        return new BaseResponse("SUCCESS", "User Updated Successfully", userEntity);
    }


    @Override
    public UserResponseDto getUserById(Integer userId) {
        Optional<User> user = this.userRepository.findById(userId);
        if (!user.isPresent()) {
            log.error("No User found for UserId: {}", userId);
            throw new ResourceNotFoundException("user", "id", userId);
        }
        return UserAdapter.convertUserEntityToUserResponseDto(user.get());
    }

    @Override
    public List<UserResponseDto> getAllUsers() {
        List<User> users = this.userRepository.findAll();
        List<UserResponseDto> userDtos = users.stream()
                .map(user -> UserAdapter.convertUserEntityToUserResponseDto(user))
                .collect(Collectors.toList());

        return userDtos;
    }

    @Override
    public void deleteUser(Integer userId) {
        Optional<User> user = this.userRepository.findById(userId);
        if (!user.isPresent()) {
            log.error("No User found for UserId: {}", userId);
            throw new ResourceNotFoundException("user", "id", userId);
        }
        this.userRepository.delete(user.get());
    }

}
