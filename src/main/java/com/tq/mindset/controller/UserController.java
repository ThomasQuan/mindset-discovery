package com.tq.mindset.controller;

import com.tq.mindset.domain.User;
import com.tq.mindset.dto.authDto.LoginUserDto;
import com.tq.mindset.dto.authDto.RegisterUserDto;
import com.tq.mindset.dto.viewDto.UserDto;
import com.tq.mindset.response.LoginResponse;
import com.tq.mindset.service.JwtService;
import com.tq.mindset.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api/users")
@RestController
public class UserController {
    private final JwtService jwtService;
    private final UserService userService;

    public UserController(UserService userService, JwtService jwtService) {
        this.userService = userService;
        this.jwtService = jwtService;
    }

    @GetMapping("/me")
    public ResponseEntity<User> authenticatedUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        User currentUser = (User) authentication.getPrincipal();

        return ResponseEntity.ok(currentUser);
    }

    @GetMapping("/")
    public ResponseEntity<List<UserDto>> allUsers() {
        List <UserDto> users = userService.allUsers();

        return ResponseEntity.ok(users);
    }

    @PostMapping("/signup")
    public ResponseEntity<UserDto> register(@RequestBody RegisterUserDto registerUserDto) {
        UserDto registeredUser = userService.signup(registerUserDto);

        return ResponseEntity.ok(registeredUser);
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> authenticate(@RequestBody LoginUserDto loginUserDto) {
        UserDto authenticatedUser = userService.authenticate(loginUserDto);

        String jwtToken = jwtService.generateToken(authenticatedUser.getUsername());

        LoginResponse loginResponse = new LoginResponse();
        loginResponse.setToken(jwtToken);

        // Convert expiration date to long (milliseconds since epoch)
        long expirationInMillis = jwtService.extractExpiration(jwtToken).getTime();
        loginResponse.setExpiresIn(expirationInMillis);

        return ResponseEntity.ok(loginResponse);
    }
}