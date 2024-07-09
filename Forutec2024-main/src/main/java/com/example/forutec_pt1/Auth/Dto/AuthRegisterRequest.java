package com.example.forutec_pt1.Auth.Dto;

import lombok.Data;

@Data
public class AuthRegisterRequest {
    String firstName;
    String lastName;
    String email;
    String password;
    Boolean isAdmin;
}
