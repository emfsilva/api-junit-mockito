package io.github.emfsilva.api.services;

import io.github.emfsilva.api.domain.User;
import io.github.emfsilva.api.domain.dto.UserDTO;

import java.util.List;

public interface UserService {

    User findById(Integer id);

    List<User> findAll();

    User create(UserDTO obj);
}
