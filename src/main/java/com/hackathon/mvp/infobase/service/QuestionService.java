package com.hackathon.mvp.infobase.service;

import com.hackathon.mvp.infobase.dto.QuestionRequest;
import com.hackathon.mvp.infobase.model.Question;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.UUID;


@Service
public interface QuestionService {
    Question submitQuestion(QuestionRequest req);

    Page<Question> listQuestions(Pageable pageable);

    Page<Question> searchQuestions(String term, Pageable pageable);

    Question findById(UUID id);
}