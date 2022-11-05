package com.example.ecommercebackend.model.payload.registration;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class RegistrationPayloadRequest {
    @NotBlank(message = "First name can not be null")
    private String firstName;

    @NotBlank(message = "Last name can not be null")
    private String lastName;

    @NotBlank(message = "Username can not be null")
    private String username;

    @NotBlank(message = "Password can not be null")
    private String password;

    @Pattern(regexp = "^[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,4}$", message = "Email must be valid")
    private String email;
}
