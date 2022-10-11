package com.anyticket.backend.service;

import com.anyticket.backend.domain.User;
import com.anyticket.backend.dto.RegisterUserDto;
import com.anyticket.backend.dto.UserDto;
import com.anyticket.backend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    @Transactional
    public Set<UserDto> findAll() {
        return userRepository.findAll().stream().map(UserDto::new).collect(Collectors.toSet());
    }

    @Override
    @Transactional
    public Optional<User> save(RegisterUserDto user) {
        User newUser = new User(user);
        return Optional.ofNullable(userRepository.save(newUser));
    }

}
