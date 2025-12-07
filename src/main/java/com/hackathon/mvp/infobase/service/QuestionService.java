package com.hackathon.mvp.infobase.service;

import com.hackathon.mvp.infobase.dto.*;
import org.springframework.stereotype.Service;


@Service
public interface QuestionService {

    QuestionListResponseDto getAllQuestions(int page, int limit, String sortBy, String tag);

    QuestionDetailDto getQuestionById(Long id);

    CreateQuestionResponseDto createQuestion(CreateQuestionRequestDto request) throws IllegalArgumentException;

    QuestionDetailDto updateQuestion(UpdateQuestionRequestDto request);
}