package dev.abhisek.SpringMvc.services;

import java.util.UUID;

import org.springframework.stereotype.Service;

import dev.abhisek.SpringMvc.entities.User;
import dev.abhisek.SpringMvc.repository.UserRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final UserRepository repository;

    public User addUser(User user) {
        user.setId(UUID.randomUUID().toString());
        return repository
                .save(user);
    }

    public User verifyUser(User user) {
        User authUser = repository
                .findFirstByUsernameAndPassword(user.getUsername(), user.getPassword()).orElseThrow();
        return authUser;
    }
}
