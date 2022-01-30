package io.github.emfsilva.api.services;

import io.github.emfsilva.api.domain.User;

import java.util.List;

public interface UserService {

    User findById(Integer id);

    List<User> findAll();
}
