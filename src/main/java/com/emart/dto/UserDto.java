package com.emart.dto;

import com.emart.validations.ValidImageName;
import jakarta.validation.constraints.*;
import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserDto {

    private String userId;

    @NotEmpty
    @Size(min=2, message="username must have minimum 2 characters !!")
    private String name;

    @NotBlank(message = "Email field may not be blank")
    @Email(message="Email address is invalid !!")
    //@Pattern(regexp="^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$", message = "Email is not as per requirement")
    private String email;

    @NotBlank
    @Size(min=3, max=15, message="Password must be of minimum 8 characters and maximum of 15 characters")
    private String password;

    @NotBlank
    private String gender;

    private String about;

    @NotBlank
    @ValidImageName(message="Image name must end with .jpg, .png, or .jpeg")
    private String imageName;

}

