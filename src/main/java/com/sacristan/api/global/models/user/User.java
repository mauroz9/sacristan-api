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

    @Column(unique = true, nullable = false)
    private String username;

    @Column(unique = true, nullable = false)
    private String email;

    @Column(nullable = false)
    private String password;

    @Enumerated(EnumType.STRING)
    private Role role;

    public User modify(User user) {
        return User.builder()
                .id(this.id)
                .password(this.password)
                .role(this.role)
                .name(user.getName())
                .lastName(user.getLastName())
                .username(user.getUsername())
                .email(user.getEmail())
                .build();
    }
}
