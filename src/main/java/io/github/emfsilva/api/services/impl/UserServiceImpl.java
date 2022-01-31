package io.github.emfsilva.api.services.impl;

import io.github.emfsilva.api.domain.User;
import io.github.emfsilva.api.domain.dto.UserDTO;
import io.github.emfsilva.api.repositories.UserRepository;
import io.github.emfsilva.api.services.UserService;
import io.github.emfsilva.api.services.exceptions.DataIntegrityViolationException;
import io.github.emfsilva.api.services.exceptions.ObjectNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService
{

    private final UserRepository userRepository;
    private final ModelMapper mapper;


    @Autowired
    public UserServiceImpl(UserRepository userRepository, ModelMapper mapper) {
        this.userRepository = userRepository;
        this.mapper = mapper;
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

    @Override
    public User create(UserDTO obj) {
        findByEmail(obj);
        return userRepository.save(mapper.map(obj, User.class));
    }

    @Override
    public User update(UserDTO userDTO) {
        findByEmail(userDTO);
        return userRepository.save(mapper.map(userDTO, User.class));
    }

    private void findByEmail(UserDTO obj) {
        Optional<User> user = userRepository.findByEmail(obj.getEmail());
            if(user.isPresent() && !user.get().getId().equals(obj.getId())) {
                throw new DataIntegrityViolationException("E-mail exist");
            }
     }
}
