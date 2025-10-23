package com.featurevote.bff.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table ( name = "boards" )
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Board {

    @Id @GeneratedValue
    private UUID id;

    @Column ( name = "name", nullable = false )
    private String name;

    @Column ( name = "description" )
    private String description;

    @ManyToOne
    @JoinColumn ( name = "owner_id" )
    private User owner;

    @Column ( name = "created_at" )
    private LocalDateTime createdAt;

    @Column ( name = "updated_at" )
    private LocalDateTime updatedAt;
}
