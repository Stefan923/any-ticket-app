package com.anyticket.backend.dto;

import com.anyticket.backend.domain.User;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class UserDto {

    private String username;
    private String email;
    private String phoneNumber;

    public UserDto(User user) {
        this.username = user.getUsername();
        this.email = user.getEmail();
        this.phoneNumber = user.getPhoneNumber();
    }

}
