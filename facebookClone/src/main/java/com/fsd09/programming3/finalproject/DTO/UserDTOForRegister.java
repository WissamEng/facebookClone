package com.fsd09.programming3.finalproject.DTO;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDTOForRegister {
    @Size(min = 4, max = 20,message = "Username must be at least 4 characters")
    @NotNull
    private String username;
    @Size(min = 6, message = "Password must be at least 6 characters long")
    @NotNull
    @Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]+$", message = "Password must contain both letters and digits")
    private String password;
    @Email
    @NotNull
    private String email;
}
