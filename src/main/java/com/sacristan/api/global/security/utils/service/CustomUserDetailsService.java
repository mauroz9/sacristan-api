package com.sacristan.api.global.security.utils.service;

import com.sacristan.api.global.entities.users.user.UserModelService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service("userDetailsService")
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final UserModelService userModelService;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return userModelService.findByEmail(email);
    }
}
