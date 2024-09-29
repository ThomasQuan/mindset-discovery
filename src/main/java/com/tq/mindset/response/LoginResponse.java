package com.tq.mindset.response;


import lombok.Data;

@Data
public class LoginResponse {
    private String token;

    private long expiresIn;

}
