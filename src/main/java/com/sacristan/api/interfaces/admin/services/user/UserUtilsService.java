package com.sacristan.api.interfaces.admin.services.user;

import com.sacristan.api.global.models.user.User;
import com.sacristan.api.global.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserUtilsService {

    private final UserRepository repository;

    public boolean passwordMatch(String password1, String password2) {
        return Objects.equals(password1, password2);
    }

    public User findById(Long id) {
        return repository.findById(id).orElseThrow(() -> new NoSuchElementException("User not found with id: " + id));
    }

    public Optional<User> findByIdOptional(Long id) {
        return repository.findById(id);
    }

    public User getReferenceById(Long id) {
        return repository.getReferenceById(id);
    }


    public Optional<User> findByEmail(String email) {
        return repository.findByEmail(email);
    }
}
