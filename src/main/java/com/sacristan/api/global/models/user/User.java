package com.sacristan.api.global.models.user;

import com.sacristan.api.global.models.user.extra.Role;
import jakarta.persistence.*;
import lombok.*;

@Entity
@AllArgsConstructor @NoArgsConstructor
@Getter @Setter
@Builder
@Table(name = "users")
public class User {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String lastName;


    private String username;
    private String email;
    private String password;

    @Enumerated(EnumType.STRING)
    private Role role;

}
