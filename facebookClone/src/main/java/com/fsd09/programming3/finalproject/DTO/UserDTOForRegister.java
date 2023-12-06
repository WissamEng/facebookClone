package com.fsd09.programming3.finalproject.DTO;

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
    private String username;
    private String password;
    private String email;
}
