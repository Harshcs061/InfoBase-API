package com.hackathon.mvp.infobase.serviceImpl;

import com.hackathon.mvp.infobase.dto.QuestionRequest;
import com.hackathon.mvp.infobase.model.Question;
import com.hackathon.mvp.infobase.service.QuestionService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class QuestionServiceImpl implements QuestionService {
    @Override
    public Question submitQuestion(QuestionRequest req) {
        return null;
    }

    @Override
    public Page<Question> listQuestions(Pageable pageable) {
        return null;
    }

    @Override
    public Page<Question> searchQuestions(String term, Pageable pageable) {
        return null;
    }

    @Override
    public Question findById(UUID id) {
        return null;
    }
}
