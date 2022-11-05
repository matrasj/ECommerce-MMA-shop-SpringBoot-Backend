package com.example.ecommercebackend.model.payload.registration;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

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
    @Size(min = 4, max = 25, message = "Username must be longer than 4 and less than 25 characters")
    private String username;

    @NotBlank(message = "Password can not be null")
    @Size(min = 4, max = 25, message = "Password must be longer than 4 and less than 25 characters")
    private String password;

    @Pattern(regexp = "^[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,4}$", message = "Email must be valid")
    private String email;
}
