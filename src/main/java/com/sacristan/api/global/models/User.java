package com.sacristan.api.global.models;

import com.sacristan.api.global.models.extra.Roles;
import jakarta.persistence.*;
import lombok.*;

@Entity
@AllArgsConstructor @RequiredArgsConstructor
@Getter @Setter
@Builder
public class User {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String lastName;


    private String username;
    private String email;
    private String password;

    private Roles role;

}
