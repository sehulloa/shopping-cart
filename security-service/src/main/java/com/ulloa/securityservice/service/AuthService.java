package com.ulloa.securityservice.service;

import com.ulloa.securityservice.config.JwtUtils;
import com.ulloa.securityservice.dto.AuthRequest;
import com.ulloa.securityservice.dto.AuthResponse;
import com.ulloa.securityservice.dto.UserDto;
import com.ulloa.securityservice.entity.User;
import com.ulloa.securityservice.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private final UserRepository userRepository;
    private final JwtUtils jwtUtils;

    public AuthService(UserRepository userRepository, JwtUtils jwtUtils) {
        this.userRepository = userRepository;
        this.jwtUtils = jwtUtils;
    }

    public AuthResponse login(AuthRequest authRequest) {
        User user = userRepository.findByUsername(authRequest.getUsername())
                .orElseThrow(() -> new SecurityException("Invalid username or password"));

        if (!user.getPassword().equals(authRequest.getPassword())) {
            throw new SecurityException("Invalid username or password");
        }

        String token = jwtUtils.generateToken(new UserDto(user.getUsername(), user.getEmail()));
        return new AuthResponse(token);
    }

    public UserDto register(UserDto userDto) {
        User user = new User(userDto.getUsername(), userDto.getPassword(), userDto.getEmail());
        userRepository.save(user);
        return new UserDto(user.getUsername(), user.getEmail());
    }
}


