package com.testvagrant.ApiValidationn.dto;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserRequest {

    @NotNull(message = "username should not be null")
    private String name;
    @Email(message = "invalid email address")
    private String email;
    @NotNull(message = "mobile number should not be null")
    private  String mobile;
    private String gender;
    private int age;
    @NotBlank
    private String nationality;

}
