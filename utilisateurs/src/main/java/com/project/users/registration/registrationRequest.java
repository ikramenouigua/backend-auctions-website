package com.project.users.registration;

import lombok.*;

@Data
public class registrationRequest {
    private  String firstName;
    private  String lastName;
    private  String email;
    private  String adresse;
    private  String phone;
    private  String password;
    private boolean role;
}
