package com.hackathon.mvp.infobase.mapper;

import com.hackathon.mvp.infobase.dto.AnswerDto;
import com.hackathon.mvp.infobase.dto.AuthorDto;
import com.hackathon.mvp.infobase.model.Answer;
import com.hackathon.mvp.infobase.model.User;

public class AnswerMapper {

    public static AnswerDto toDto(Answer answer) {
        if (answer == null) return null;

        int score = answer.getUpvote() - answer.getDownvote();
        
        return AnswerDto.builder()
                .id(answer.getId() != null ? answer.getId().toString() : null)
                .questionId(answer.getQuestion() != null ? answer.getQuestion().getId() : null)
                .author(toAuthorDto(answer.getAuthor()))
                .body(answer.getBody())
                .upvotes(answer.getUpvote())
                .downvotes(answer.getDownvote())
                .score(score)
                .isAccepted(answer.isAccepted())
                .createdAt(answer.getCreatedAt())
                .updatedAt(answer.getUpdatedAt())
                .isEdited(answer.isEdited())
                .build();
    }

    private static AuthorDto toAuthorDto(User user) {
        if (user == null) return null;
        
        return AuthorDto.builder()
                .id(user.getId() != null ? user.getId().toString() : null)
                .name(user.getName())
                .avatar(user.getEmail())
                .build();
    }
}