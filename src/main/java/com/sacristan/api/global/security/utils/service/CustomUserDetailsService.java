package com.sacristan.api.global.security.utils.service;

import com.sacristan.api.interfaces.admin.services.user.UserUtilsService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service("userDetailsService")
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final UserUtilsService userUtilsService;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return userUtilsService.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with email: " + email));
    }
}
