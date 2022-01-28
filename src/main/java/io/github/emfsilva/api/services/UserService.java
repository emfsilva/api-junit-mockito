package io.github.emfsilva.api.services;

import io.github.emfsilva.api.domain.User;

public interface UserService {

    User findById(Integer id);

}
