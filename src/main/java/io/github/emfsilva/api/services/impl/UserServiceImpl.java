package io.github.emfsilva.api.services.impl;

import io.github.emfsilva.api.domain.User;
import io.github.emfsilva.api.repositories.UserRepository;
import io.github.emfsilva.api.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

public class UserServiceImpl implements UserService
{


    private UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User findById(Integer id) {
        Optional<User> obj = userRepository.findById(id);
        return obj.orElse(null);
    }
}
