package com.hackathon.mvp.infobase.service;

import com.hackathon.mvp.infobase.dto.AnswerRequest;
import com.hackathon.mvp.infobase.dto.SearchResponse;
import com.hackathon.mvp.infobase.model.Answer;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public interface AnswerService {
    Answer submitAnswer(UUID questionId, AnswerRequest req);

    List<SearchResponse.AnswerResult> searchAnswers(String term);
}