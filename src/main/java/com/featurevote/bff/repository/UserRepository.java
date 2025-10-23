package com.featurevote.bff.repository;

import com.featurevote.bff.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

    User findByEmail(String email);

    // Additional methods can be added as required
}
