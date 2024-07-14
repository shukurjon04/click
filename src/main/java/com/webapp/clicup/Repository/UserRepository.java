package com.webapp.clicup.Repository;

import com.webapp.clicup.Entity.user;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<user, UUID> {
    Optional<user> findByEmail(String email);
    boolean existsByEmail(String email);
}
