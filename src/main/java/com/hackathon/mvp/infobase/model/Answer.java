package com.hackathon.mvp.infobase.model;

import com.hackathon.mvp.infobase.enums.ContentType;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "answers")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Answer  {
    @Id
    @Column(columnDefinition = "uuid")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "question_id")
    private Question question;

    @Column(columnDefinition = "text")
    private String body;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "author_id")
    private User author;

    private LocalDateTime createdAt;

    @Column(columnDefinition = "votes")
    private int votes;

    @Column(columnDefinition = "upvote")
    private int upvote;

    @Column(columnDefinition = "downvote")
    private int downvote;

    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
    }

    public String getContent() {
        return body;
    }

    public ContentType getContentType() {
        return ContentType.ANSWER;
    }
}