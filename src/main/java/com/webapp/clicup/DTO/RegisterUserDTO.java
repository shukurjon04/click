package com.webapp.clicup.DTO;

import jakarta.persistence.Column;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@AllArgsConstructor
@NoArgsConstructor
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class RegisterUserDTO {
    @NotBlank(message = "fullname cannot empty")
    @Size(min = 6, max = 100, message = "fullname error")
    String FullName;
    @NotBlank(message = "this field must not empty")
    @Size(min = 10, max = 100, message = "email error")
    @Email(regexp = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$", message = "email is not  valid")
    String email;
    @NotBlank(message = "password cannot empty")
    @Size(min = 6, max = 100, message = "password error")
    String password;
}
