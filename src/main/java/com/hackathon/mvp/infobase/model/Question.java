package com.hackathon.mvp.infobase.model;

import com.hackathon.mvp.infobase.enums.ContentType;
import com.hackathon.mvp.infobase.enums.Mentionable;
import com.hackathon.mvp.infobase.enums.QuestionVisibility;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Table(name = "questions")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Question implements Mentionable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    @Column(length = 5000)
    private String description;

    @ManyToMany
    @JoinTable(
            name = "question_tags",
            joinColumns = @JoinColumn(name = "question_id"),
            inverseJoinColumns = @JoinColumn(name = "tag_id")
    )
    private Set<Tag> tags;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "asked_by_id")
    private User askedBy;

    @Enumerated(EnumType.STRING)
    private QuestionVisibility visibility;

    private int votes;

    private int answersCount;

    private int views;

    private int upvote;

    private int downvote;

    private LocalDateTime createdAt;

    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
    }

    @Override
    public String getContent() {
        return description;
    }

    @Override
    public ContentType getContentType() {
        return ContentType.QUESTION;
    }
}