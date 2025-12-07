package com.hackathon.mvp.infobase.model;


import com.hackathon.mvp.infobase.enums.ContentType;
import com.hackathon.mvp.infobase.enums.VoteType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name = "votes", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"user_id", "question_id"}),
        @UniqueConstraint(columnNames = {"user_id", "answer_id"})
})
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Vote {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_id", nullable = false)
    private Long userId;

    @Column(name = "votingId",nullable = false)
    private Long votingId;

    @Enumerated(EnumType.STRING)
    @Column(name = "votedOn",nullable = false)
    ContentType votedOn;

    @Enumerated(EnumType.STRING)
    @Column(name = "vote_type", nullable = false)
    private VoteType voteType;

}