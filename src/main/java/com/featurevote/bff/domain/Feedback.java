package com.featurevote.bff.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "feedback")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Feedback {

    @Id @GeneratedValue
    private UUID id;

    @Column (name = "title", nullable = false)
    private String title;

    @Column (name = "description", nullable = false)
    private String description;

    @Enumerated (EnumType.STRING)
    private Status status;

    @ManyToOne
    @JoinColumn ( name = "user_id" )
    private User owner;

    @ManyToOne
    @JoinColumn(name = "board_id")
    private Board board;

    @Column (name = "created_at")
    private LocalDateTime createdAt;

    @Column (name = "updated_at")
    private LocalDateTime updatedAt;

    public enum Status {
        SUBMITTED,
        PLANNED,
        IN_PROGRESS,
        DONE
    }

}
