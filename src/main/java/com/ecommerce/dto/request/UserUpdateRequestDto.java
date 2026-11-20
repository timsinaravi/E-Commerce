package com.ecommerce.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserUpdateRequestDto {


        @NotBlank(message = "First Name cannot be blank")
        private String firstName;

        @NotBlank(message = "Last Name cannot be blank")
        private String lastName;

        @NotBlank(message = "Email cannot be blank")
        @Email(message="Invalid email")
        private String email;

        @NotBlank(message = "Phone number is required")
        private String phone;

        @NotBlank(message = "Address cannot be blank")
        private String address;


    }
