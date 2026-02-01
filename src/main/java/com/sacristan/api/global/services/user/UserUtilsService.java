package com.sacristan.api.global.services.user;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
@RequiredArgsConstructor
public class UserUtilsService {

    public boolean passwordMatch(String password1, String password2) {
        return Objects.equals(password1, password2);
    }


}
