package com.anyticket.backend.service;

import com.anyticket.backend.dto.UserDto;

import java.util.Set;

public interface UserService {

    Set<UserDto> findAll();

}
