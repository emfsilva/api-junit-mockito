package io.github.emfsilva.api.services.impl;

import io.github.emfsilva.api.domain.User;
import io.github.emfsilva.api.domain.dto.UserDTO;
import io.github.emfsilva.api.repositories.UserRepository;
import io.github.emfsilva.api.services.exceptions.DataIntegrityViolationException;
import io.github.emfsilva.api.services.exceptions.ObjectNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

class UserServiceImplTest {

    public static final Integer ID      = 1;
    public static final String NAME     = "Emerson";
    public static final String EMAIL    = "emerson@gmail.com";
    public static final String PASSWORD = "123";
    public static final String USER_NOT_FOUND = "User not found";
    public static final int INDEX = 0;
    public static final String E_MAIL_EXIST = "E-mail exist";

    @InjectMocks
    private UserServiceImpl service;

    @Mock
    private UserRepository repository;

    @Mock
    private ModelMapper mapper;

    private User user;
    private UserDTO userDTO;
    private Optional<User> userOptinal;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        startUser();
    }

    @Test
    void WhenFindByIdThenReturnAnUserInstance() {
        when(repository.findById(anyInt())).thenReturn(userOptinal);

        User response = service.findById(ID);

        assertNotNull(response);
        assertEquals(User.class, response.getClass());
        assertEquals(ID, response.getId());
        assertEquals(NAME, response.getName());
        assertEquals(EMAIL, response.getEmail());
    }

    @Test
    void WhenFindByIdThenReturnAnObjectNotFoundException() {
        when(repository.findById(anyInt())).thenThrow(new ObjectNotFoundException(USER_NOT_FOUND));

        try{
            service.findById(ID);
        }catch (Exception ex) {
            assertEquals(ObjectNotFoundException.class, ex.getClass());
            assertEquals(USER_NOT_FOUND, ex.getMessage());
        }
    }

    @Test
    void whenFindAllThenReturnAnListOfUsers() {
        when(repository.findAll()).thenReturn(List.of(user));
        List<User> response = service.findAll();
        assertNotNull(response);
        assertEquals(1, response.size());
        assertEquals(User.class, response.get(INDEX).getClass());
        assertEquals(ID, response.get(INDEX).getId());
        assertEquals(NAME, response.get(INDEX).getName());
        assertEquals(EMAIL, response.get(INDEX).getEmail());
        assertEquals(PASSWORD, response.get(INDEX).getPassword());
    }

    @Test
    void whenCreateThenReturnSuccess() {
        when(repository.save(any())).thenReturn(user);

        User response = service.create(userDTO);

        assertNotNull(response);
        assertEquals(User.class, response.getClass());
        assertEquals(ID, response.getId());
        assertEquals(NAME, response.getName());
        assertEquals(EMAIL, response.getEmail());
        assertEquals(PASSWORD, response.getPassword());
    }

    @Test
    void whenCreateThenReturnDataIntegrityViolationException() {
        when(repository.findByEmail(anyString())).thenReturn(userOptinal);

        try {
            userOptinal.get().setId(2);
            service.create(userDTO);
        }catch (Exception ex) {
            assertEquals(DataIntegrityViolationException.class, ex.getClass());
            assertEquals(E_MAIL_EXIST, ex.getMessage());
        }
    }

    @Test
    void whenUpdateThenReturnSuccess() {
        when(repository.save(any())).thenReturn(user);

        User response = service.update(userDTO);

        assertNotNull(response);
        assertEquals(User.class, response.getClass());
        assertEquals(ID, response.getId());
        assertEquals(NAME, response.getName());
        assertEquals(EMAIL, response.getEmail());
        assertEquals(PASSWORD, response.getPassword());
    }


    @Test
    void whenUpdateThenReturnDataIntegrityViolationException() {
        when(repository.findByEmail(anyString())).thenReturn(userOptinal);

        try {
            userOptinal.get().setId(2);
            service.create(userDTO);
        }catch (Exception ex) {
            assertEquals(DataIntegrityViolationException.class, ex.getClass());
            assertEquals(E_MAIL_EXIST, ex.getMessage());
        }
    }



    @Test
    void whenDeleteThenReturnSuccess() {
        when(repository.findById(anyInt())).thenReturn(userOptinal);
        doNothing().when(repository).deleteById(anyInt());
        service.delete(ID);

        verify(repository, times(1)).deleteById(anyInt());
    }

    @Test
    void deleteWithObjectNotFoundException() {
        when(repository.findById(anyInt())).thenThrow(new ObjectNotFoundException(USER_NOT_FOUND));
        try {
            service.delete(ID);
        }catch (Exception ex) {
            assertEquals(ObjectNotFoundException.class, ex.getClass());
            assertEquals(USER_NOT_FOUND, ex.getMessage());
        }

    }

    private void startUser() {
        user = new User(ID, NAME, EMAIL, PASSWORD);
        userDTO = new UserDTO(ID, NAME, EMAIL, PASSWORD);
        userOptinal = Optional.of(new User(ID, NAME, EMAIL, PASSWORD));
    }
}