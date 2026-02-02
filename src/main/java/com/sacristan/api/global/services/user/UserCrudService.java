package com.sacristan.api.global.services.user;

import com.sacristan.api.global.models.user.User;
import com.sacristan.api.global.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class UserCrudService {


    /* ON THE IMMEDIATE FUTURE WE WILL ADD VALIDATION DIRECTLY ON TO THE CLASSES AND GLOBAL EXCEPTION HANDLING */

    private final UserRepository repository;

    public User create(User user) {
        return repository.save(user);
    }

    public User read(long id) {
        return repository
                .findById(id)
                .orElseThrow(() -> new NoSuchElementException("User not found with id: " + id));
    }

    public User update(Long id, User user) {

        User oldUser = repository
                .findById(id)
                .orElseThrow(() -> new NoSuchElementException("User not found with id: " + id));

        return repository.save(oldUser.modify(user));

    }

    public void delete(long id) {
        repository.deleteById(id);
    }

    public Page<User> list(Pageable pageable) {

        Page<User> users = repository.findAll(pageable);

        if (users.isEmpty())
            throw new NoSuchElementException("No users found");

        return users;
    }
}
