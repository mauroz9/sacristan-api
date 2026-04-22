package com.sacristan.api.global.entities.users.user;

import com.sacristan.api.global.entities.users.role.Role;
import com.sacristan.api.global.services.BaseServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;
import java.util.Optional;


@Service
@RequiredArgsConstructor
public class UserModelService extends BaseServiceImpl<User, Long, UserRepository> {

    private final PasswordEncoder passwordEncoder;

    public User findByEmail(String email) {
        return repository.findByEmail(email)
                .orElseThrow(() -> new NoSuchElementException("User not found with email: " + email));
    }

    // SPECIAL METHOD: Requerido por la seguridad.
    // Si no fuera especial, tendría el handling de los errores, pero nos interesa devolver optional.
    public Optional<User> findById(Long userId) {
        return repository.findById(userId);
    }

    public User create(User user, Role role) {
        user.getRoles().add(role);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return this.save(user);
    }

    public User update(Long id, User user) {
        User oldUser = this.getById(id);
        return this.save(oldUser.modify(user));
    }
}



