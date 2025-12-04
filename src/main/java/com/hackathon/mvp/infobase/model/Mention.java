package com.hackathon.mvp.infobase.model;

import com.hackathon.mvp.infobase.enums.ContentType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "mentions")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Mention {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ContentType contentType;

    @Column(nullable = false)
    private Long contentId;

    @ManyToOne
    @JoinColumn(name = "tagged_user_id", nullable = false)
    private User taggedUser;

    @ManyToOne
    @JoinColumn(name = "mentioned_by_user_id", nullable = false)
    private User mentionedBy;

    private LocalDateTime createdAt;

    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
    }
}