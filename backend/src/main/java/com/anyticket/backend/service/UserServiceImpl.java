package com.anyticket.backend.service;

import com.anyticket.backend.domain.User;
import com.anyticket.backend.dto.RegisterUserDto;
import com.anyticket.backend.dto.UserDto;
import com.anyticket.backend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Collections;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    private static final int MAX_PAGE_SIZE = 100;

    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    @Transactional
    public Set<UserDto> findAll(int pageNumber, int pageSize, String sortBy) {
        Pageable pageable = PageRequest.of(pageNumber, Math.min(pageSize, MAX_PAGE_SIZE), Sort.by(sortBy));
        Page<User> page = userRepository.findAll(pageable);

        if (page.hasContent()) {
            return page.stream().map(UserDto::new).collect(Collectors.toSet());
        }
        return Collections.emptySet();
    }

    @Override
    @Transactional
    public Optional<User> save(RegisterUserDto user) {
        try {
            return Optional.of(userRepository.save(new User(user)));
        } catch (Exception ex) {
            return Optional.empty();
        }
    }

}
