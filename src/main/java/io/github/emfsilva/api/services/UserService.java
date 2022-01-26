package io.github.emfsilva.api.services;

import io.github.emfsilva.api.domain.User;
import org.springframework.stereotype.Service;

@Service
public interface UserService {

    User findById(Integer id);

}
