package com.hackathon.mvp.infobase.serviceImpl;

import com.hackathon.mvp.infobase.dto.AnswerRequest;
import com.hackathon.mvp.infobase.dto.SearchResponse;
import com.hackathon.mvp.infobase.model.Answer;
import com.hackathon.mvp.infobase.service.AnswerService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class AnswerServiceImpl implements AnswerService {
    @Override
    public Answer submitAnswer(UUID questionId, AnswerRequest req) {
        return null;
    }

    @Override
    public List<SearchResponse.AnswerResult> searchAnswers(String term) {
        return List.of();
    }
}
