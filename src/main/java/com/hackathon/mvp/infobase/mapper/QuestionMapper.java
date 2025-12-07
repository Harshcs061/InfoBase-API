package com.hackathon.mvp.infobase.mapper;

import com.hackathon.mvp.infobase.dto.*;
import com.hackathon.mvp.infobase.model.Question;
import com.hackathon.mvp.infobase.model.Tag;
import com.hackathon.mvp.infobase.model.User;

import java.util.List;

public class QuestionMapper {

    public static AuthorDto toAuthorDto(User user) {
        if (user == null) return null;
        return AuthorDto.builder()
                .id(user.getId() != null ? user.getId().toString() : null)
                .name(user.getName())
                .avatar(user.getEmail())
                .build();
    }

    public static QuestionListItemDto toListItemDto(Question q) {
        int votes = q.getUpvote()- q.getDownvote();
        votes = votes > 0 ?votes:0;
        return QuestionListItemDto.builder()
                .id(q.getId().toString())
                .title(q.getTitle())
                .content(q.getDescription()) // maps to "content"
                .tags(
                        q.getTags() == null
                                ? List.of()
                                : q.getTags()
                                .stream()
                                .map(Tag::getName)
                                .toList()
                )
                .author(toAuthorDto(q.getAskedBy()))
                .votes(votes)
                .answersCount(q.getAnswersCount())
                .createdAt(q.getCreatedAt())
                .build();
    }

    public static AskedByDto toAskedByDto(User user) {
        if (user == null) return null;

        String name = user.getName();

        return AskedByDto.builder()
                .id(user.getId())
                .name(user.getName())
                .avatar(user.getEmail())
                .build();
    }

    public static QuestionStatsDto toStatsDto(Question q) {
        int votes = q.getUpvote()- q.getDownvote();
        votes = votes > 0 ?votes:0;
        return QuestionStatsDto.builder()
                .votes(votes)
                .answers(q.getAnswersCount())
                .views(q.getViews())
                .build();
    }

    public static QuestionDetailDto toDetailDto(Question q) {
        return QuestionDetailDto.builder()
                .id(q.getId().toString())
                .title(q.getTitle())
                .body(q.getDescription())   // maps to "body"
                .tags(
                        q.getTags() == null
                                ? List.of()
                                : q.getTags()
                                .stream()
                                .map(Tag::getName)
                                .toList()
                )
                .askedBy(toAskedByDto(q.getAskedBy()))
                .stats(toStatsDto(q))
                .createdAt(q.getCreatedAt())
                .build();
    }

    public static QuestionDetailForCreateDto toDetailForCreateDto(Question q) {
        return QuestionDetailForCreateDto.builder()
                .id(q.getId())
                .title(q.getTitle())
                .description(q.getDescription())
                .tags(
                        q.getTags() == null
                                ? List.of()
                                : q.getTags()
                                .stream()
                                .map(Tag::getName)
                                .toList()
                )
                .visibility(q.getVisibility().name().toLowerCase())
                .askedBy(toAuthorDto(q.getAskedBy()))
                .createdAt(q.getCreatedAt())
                .build();
    }
}