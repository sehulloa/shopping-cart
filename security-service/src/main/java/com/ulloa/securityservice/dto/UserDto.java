package com.ulloa.securityservice.dto;

import lombok.Data;

@Data
public class UserDto {

    private String username;
    private String password;
    private String email;

    public UserDto(String username, String email) {
    }
}
