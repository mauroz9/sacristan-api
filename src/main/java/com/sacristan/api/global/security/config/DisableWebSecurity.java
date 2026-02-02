package com.sacristan.api.global.security.config;

import org.springframework.context.annotation.Configuration;

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
