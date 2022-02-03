package io.github.emfsilva.api.controller.exception;

import io.github.emfsilva.api.services.exceptions.DataIntegrityViolationException;
import io.github.emfsilva.api.services.exceptions.ObjectNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletRequest;

import java.time.LocalDateTime;
import java.time.Month;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ResourceExceptionHandlerTest {

    public static final String USER_NOT_FOUND = "user not found";
    public static final String E_MAIL_EXIST = "E-mail exist";



    @InjectMocks
    private ResourceExceptionHandler resourceExceptionHandler;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void whenObjectNotFoundExceptionThenReturnResponseEntity() {

        LocalDateTime hora = LocalDateTime.of(2016, Month.APRIL, 4, 22, 30);

        ResponseEntity<StandardError> response = resourceExceptionHandler
                .objectNotFound(new ObjectNotFoundException(USER_NOT_FOUND), new MockHttpServletRequest());

        assertNotNull(response);
        assertNotNull(response.getBody());
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals(ResponseEntity.class, response.getClass());
        assertEquals(StandardError.class, response.getBody().getClass());
        assertEquals(USER_NOT_FOUND, response.getBody().getError());
        assertEquals(404, response.getBody().getStatus());
        assertNotEquals("/user/2", response.getBody().getPath());
        assertNotEquals(hora, response.getBody().getTimestamp());

    }

    @Test
    void dataIntegrityViolation() {
        ResponseEntity<StandardError> response = resourceExceptionHandler
                .dataIntegrityViolation(new DataIntegrityViolationException(E_MAIL_EXIST), new MockHttpServletRequest());

        assertNotNull(response);
        assertNotNull(response.getBody());
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals(ResponseEntity.class, response.getClass());
        assertEquals(StandardError.class, response.getBody().getClass());
        assertEquals(E_MAIL_EXIST, response.getBody().getError());
        assertEquals(400, response.getBody().getStatus());

    }
}