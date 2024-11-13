package com.userService.DTOs;

import lombok.Data;
import org.springframework.stereotype.Component;

@Data

public class UserRegistrationDTO {

    private String firstName;
    private String lastName;
    private String email;
    private String password;
}
