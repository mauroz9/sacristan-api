package com.sacristan.api.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.boot.security.autoconfigure.web.servlet.PathRequest.toH2Console;

@Configuration
public class DisableWebSecurity {

//    @Bean
//    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//
//        http
//            .csrf(csrf -> csrf
//                .ignoringRequestMatchers(toH2Console())
//                .disable()
//            )
//            .headers(headers -> headers
//                .frameOptions(HeadersConfigurer.FrameOptionsConfig::sameOrigin)
//            )
//            .authorizeHttpRequests(auth ->auth
//                    .requestMatchers(toH2Console()).permitAll()
//                    .anyRequest().permitAll()
//                );
//
//        return http.build();
//    }

}
