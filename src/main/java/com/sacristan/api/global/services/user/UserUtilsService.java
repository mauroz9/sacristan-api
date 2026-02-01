package com.sacristan.api.global.services.user;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

@Service
@RequiredArgsConstructor
public class UserUtilsService {

    public boolean passwordMatch(String password1, String password2) {
        return password1.equals(password2);
    }


}
