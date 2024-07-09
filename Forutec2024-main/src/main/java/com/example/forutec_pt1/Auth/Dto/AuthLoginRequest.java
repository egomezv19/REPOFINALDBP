package com.example.forutec_pt1.Auth.Dto;

import lombok.Data;

@Data
public class AuthLoginRequest {
    public String email;
    public String password;
}