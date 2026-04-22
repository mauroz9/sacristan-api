package com.sacristan.api.global.security.config.jwt.refresh;

import com.sacristan.api.global.entities.users.user.User;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RefreshTokenRepository extends JpaRepository<RefreshToken, Long> {

    Optional<RefreshToken> findByToken(String token);

    @Modifying
    @EntityGraph(attributePaths = {"user"})
    int deleteByUser(User user);

}
