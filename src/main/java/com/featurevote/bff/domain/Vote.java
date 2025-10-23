package com.featurevote.bff.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "votes")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Vote {

    @Id
    @GeneratedValue
    private UUID id;

    @ManyToOne
    @JoinColumn(name="feedback_id")
    private Feedback feedback;

    @OneToOne
    @JoinColumn(name="user_id")
    private User owner;

    @Column(name = "vote_type")
    private String voteType;

    @Column(name = "created_at")
    private LocalDateTime createdAt;
}
