package com.anyticket.backend.dto;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class RegisterUserDto {

    private String username;
    private String password;
    private String email;
    private String phoneNumber;

}
