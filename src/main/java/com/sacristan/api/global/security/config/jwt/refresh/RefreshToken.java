package com.sacristan.api.global.security.config.jwt.refresh;

import com.sacristan.api.global.entities.users.user.User;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.NaturalId;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.Instant;

@Entity
@AllArgsConstructor @NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
@Getter @Setter
@Builder
public class RefreshToken {

    @Id
    private Long id;

    @MapsId
    @OneToOne()
    @JoinColumn(
        name = "user_id"
    )
    private User user;

    @NaturalId
    @Column(nullable = false, unique = true)
    private String token;

    private Instant expirationDate;

    @CreatedDate
    private Instant createdAt;

}
