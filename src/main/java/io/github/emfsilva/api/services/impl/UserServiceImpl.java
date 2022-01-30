package io.github.emfsilva.api.services.impl;

import io.github.emfsilva.api.domain.User;
import io.github.emfsilva.api.repositories.UserRepository;
import io.github.emfsilva.api.services.UserService;
import io.github.emfsilva.api.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
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
        return obj.orElseThrow( () -> new ObjectNotFoundException("User not found "));
    }

    @Override
    public List<User> findAll() {
     return userRepository.findAll();
    }
}
