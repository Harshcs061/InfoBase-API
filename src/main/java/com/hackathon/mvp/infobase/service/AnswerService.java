package com.hackathon.mvp.infobase.service;

import com.hackathon.mvp.infobase.enums.ContentType;
import com.hackathon.mvp.infobase.model.Answer;
import com.hackathon.mvp.infobase.model.Question;
import com.hackathon.mvp.infobase.model.User;
import com.hackathon.mvp.infobase.respository.AnswerRepository;
import com.hackathon.mvp.infobase.respository.QuestionRepository;
import com.hackathon.mvp.infobase.respository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
public class AnswerService {

    @Autowired
    private AnswerRepository answerRepository;

    @Autowired
    private QuestionRepository questionRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private MentionService mentionService;

    @Transactional
    public Answer createAnswer(Long userId, Long questionId, String content) {
        User user = userRepository.findById(userId);

        Question question = questionRepository.findById(questionId)
                .orElseThrow(() -> new IllegalArgumentException("Question not found"));


        Answer answer = new Answer();
        answer.setAuthor(user);
        answer.setQuestion(question);
        answer.setBody(content);
        Answer saved = answerRepository.save(answer);

        // Process @mentions in answer content
        mentionService.processMentions(
                ContentType.ANSWER,
                userId,
                content,
                user
        );

        log.info("Answer created: ID {} with mentions", saved.getId());
        return saved;
    }
}
