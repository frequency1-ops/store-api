package com.tech2tech.store.dtos;

import com.tech2tech.store.validation.LowerCase;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;



@Data
public class RegisterUserRequest {

    @NotBlank(message = "Name is required")
    @Size(max=255)
    private String name;

    @NotBlank(message = "Email is required")
    @Email(message = "Email must be valid")
    @LowerCase
    private String email;

    @NotBlank
    @Size(min = 6, max = 20, message = "Password must be atleast 6 characters and a max of 20 characters")
    private String password;
}
