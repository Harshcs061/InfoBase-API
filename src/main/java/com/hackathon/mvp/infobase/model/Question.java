package com.hackathon.mvp.infobase.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.Instant;
import java.util.UUID;

@Entity
@Table(name = "questions")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Question {
    @Id
    @Column(columnDefinition = "uuid")
    private UUID id;

    @Column(columnDefinition = "text", nullable = false)
    private String title;

    @Column(columnDefinition = "text")
    private String body;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "author_id")
    private User author;

    private Instant createdAt;
}