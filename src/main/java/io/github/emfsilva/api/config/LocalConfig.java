package io.github.emfsilva.api.config;

import io.github.emfsilva.api.domain.User;
import io.github.emfsilva.api.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.util.List;

@Configuration
@Profile("local")
public class LocalConfig {

    private final UserRepository userRepository;

    @Autowired
    public LocalConfig(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Bean
    public void startDB() {
        User u1 = new User(null, "Valdir", "valdir@gmail.com", "123");
        User u2 = new User(null, "Emerson", "emersonferreira@gmail.com", "123");

        userRepository.saveAll(List.of(u1,u2));
    }
}
