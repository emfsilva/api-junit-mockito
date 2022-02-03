package io.github.emfsilva.api.domain;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class UserTest {

    private static final Integer ID      = 1;
    private static final String NAME     = "Valdir";
    private static final String EMAIL    = "valdir@mail.com";
    private static final String PASSWORD = "123";


    @Test
    void testEquals() {
        User user1 = new User(ID, NAME, EMAIL, PASSWORD);
        User user2 = new User(ID, NAME, EMAIL, PASSWORD);

        assertThat(user1).isEqualTo(user2);

    }

    @Test
    void testHashCode() {
        User user1 = new User(ID, NAME, EMAIL, PASSWORD);
        User user2 = new User(ID, NAME, EMAIL, PASSWORD);

        int hashCode1 = user1.hashCode();
        int hashCode2 = user2.hashCode();

        assertThat(hashCode1).isEqualTo(hashCode2);
    }
}