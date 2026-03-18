package com.sacristan.api.global.entities.users.user;

import com.sacristan.api.global.services.BaseServiceImpl;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;
import java.util.Optional;


@Service
public class UserModelService extends BaseServiceImpl<User, Long, UserRepository> {

    public User findByEmail(String email) {
        return repository.findByEmail(email)
                .orElseThrow(() -> new NoSuchElementException("User not found with email: " + email));
    }

    // SPECIAL METHOD: Requerido por la seguridad.
    // Si no fuera especial, tendría el handling de los errores, pero nos interesa devolver optional.
    public Optional<User> findById(Long userId) {
        return repository.findById(userId);
    }
}



