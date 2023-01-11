package com.project.users.registration;

import lombok.Data;

@Data
public class loginRequest {
    private String usernameOrEmail;
    private String password;
}