package com.featurevote.bff.domain;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table ( name = "users" )
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {

    @Id @GeneratedValue
    private UUID id;

    @Column ( name = "username", nullable = false, unique = true )
    private String username;

    @Column ( name = "password", nullable = false )
    private String password;

    @Column ( name = "email", nullable = false, unique = true )
    private String email;

    @Column ( name = "role", nullable = false )
    @Enumerated ( EnumType.STRING )
    private Role role;

    @Column ( name = "created_at" )
    private LocalDateTime createdAt = LocalDateTime.now();

    public enum Role {
        USER,
        ADMIN
    }
}
