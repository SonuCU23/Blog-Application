package com.blog.app.request;

import lombok.Data;

@Data
public class UpdateUserRequestDto {
    private String name;
    private String userName;
    private String email;
    private String about;
}
