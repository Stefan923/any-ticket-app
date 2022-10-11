package com.anyticket.backend.service;

import com.anyticket.backend.domain.User;
import com.anyticket.backend.dto.RegisterUserDto;
import com.anyticket.backend.dto.UserDto;

import java.util.Optional;
import java.util.Set;

public interface UserService {

    Set<UserDto> findAll(int pageNumber, int pageSize, String sortBy);
    Optional<User> save(RegisterUserDto user);

}
