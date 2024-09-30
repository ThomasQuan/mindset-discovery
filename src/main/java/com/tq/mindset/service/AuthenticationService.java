package com.tq.mindset.service;

import com.tq.mindset.dto.authDto.LoginUserDto;
import com.tq.mindset.dto.authDto.RegisterUserDto;
import com.tq.mindset.domain.User;
import com.tq.mindset.repository.UserRepository;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {

}