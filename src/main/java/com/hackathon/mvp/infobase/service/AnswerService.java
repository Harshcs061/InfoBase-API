package com.hackathon.mvp.infobase.service;

import com.hackathon.mvp.infobase.dto.*;
import com.hackathon.mvp.infobase.enums.ContentType;
import com.hackathon.mvp.infobase.mapper.AnswerMapper;
import com.hackathon.mvp.infobase.model.Answer;
import com.hackathon.mvp.infobase.model.Question;
import com.hackathon.mvp.infobase.model.User;
import com.hackathon.mvp.infobase.respository.AnswerRepository;
import com.hackathon.mvp.infobase.respository.QuestionRepository;
import com.hackathon.mvp.infobase.respository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Slf4j
@Transactional
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
        Optional<User> user = userRepository.findById(userId);

    /**
     * 2.2 Get Answer by ID
     */
    @Transactional(readOnly = true)
    public AnswerResponseDto getAnswerById(Long id) {
        Answer answer = answerRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Answer not found with id: " + id));

        AnswerDto answerDto = AnswerMapper.toDto(answer);

        return AnswerResponseDto.builder()
                .success(true)
                .data(answerDto)
                .build();
    }

    /**
     * 2.3 Create Answer
     */
    public AnswerResponseDto createAnswer(Long questionId, String userEmail, CreateAnswerRequestDto request) {
        // Validate question exists
        Question question = questionRepository.findById(questionId)
                .orElseThrow(() -> new EntityNotFoundException("Question not found with id: " + questionId));

        // Get authenticated user
        User user = userRepository.findByEmail(userEmail)
                .orElseThrow(() -> new EntityNotFoundException("User not found with email: " + userEmail));

        // Create answer
        Answer answer = Answer.builder()
                .question(question)
                .author(user)
                .body(request.getBody())
                .upvote(0)
                .downvote(0)
                .votes(0)
                .build();

        Answer answer = new Answer();
        answer.setAuthor(user.get());
        answer.setQuestion(question);
        answer.setBody(content);
        Answer saved = answerRepository.save(answer);

//        // Process @mentions in answer content
//        mentionService.processMentions(
//                ContentType.ANSWER,
//                userId,
//                content,
//                user
//        );

        log.info("Answer created: ID {} for question {} by user {}", 
                savedAnswer.getId(), questionId, user.getEmail());

        AnswerDto answerDto = AnswerMapper.toDto(savedAnswer);

        return AnswerResponseDto.builder()
                .success(true)
                .data(answerDto)
                .build();
    }

    /**
     * 2.4 Update Answer
     */
    public AnswerResponseDto updateAnswer(Long id, String userEmail, UpdateAnswerRequestDto request) {
        // Get answer
        Answer answer = answerRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Answer not found with id: " + id));

        // Get authenticated user
        User user = userRepository.findByEmail(userEmail)
                .orElseThrow(() -> new EntityNotFoundException("User not found with email: " + userEmail));

        // Check if user is the author
        if (!answer.getAuthor().getId().equals(user.getId())) {
            throw new IllegalArgumentException("You are not authorized to update this answer");
        }

        // Update answer
        answer.setBody(request.getBody());
        Answer updatedAnswer = answerRepository.save(answer);

        log.info("Answer updated: ID {} by user {}", id, user.getEmail());

        AnswerDto answerDto = AnswerMapper.toDto(updatedAnswer);

        return AnswerResponseDto.builder()
                .success(true)
                .data(answerDto)
                .build();
    }

    /**
     * 2.5 Delete Answer
     */
    public DeleteResponseDto deleteAnswer(Long id, String userEmail) {
        // Get answer
        Answer answer = answerRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Answer not found with id: " + id));

        // Get authenticated user
        User user = userRepository.findByEmail(userEmail)
                .orElseThrow(() -> new EntityNotFoundException("User not found with email: " + userEmail));

        // Check if user is the author
        if (!answer.getAuthor().getId().equals(user.getId())) {
            throw new IllegalArgumentException("You are not authorized to delete this answer");
        }

        // Decrement answer count on question
        Question question = answer.getQuestion();
        if (question != null) {
            question.setAnswersCount(Math.max(0, question.getAnswersCount() - 1));
            questionRepository.save(question);
        }

        // Delete answer
        answerRepository.delete(answer);

        log.info("Answer deleted: ID {} by user {}", id, user.getEmail());

        return DeleteResponseDto.builder()
                .success(true)
                .message("Answer deleted successfully")
                .build();
    }

    // Helper method to build sort
    private Sort buildSort(String sortBy) {
        if (sortBy == null) {
            return Sort.by(Sort.Direction.DESC, "upvote");
        }

        switch (sortBy.toLowerCase()) {
            case "votes":
                return Sort.by(Sort.Direction.DESC, "upvote");
            case "newest":
                return Sort.by(Sort.Direction.DESC, "createdAt");
            case "oldest":
                return Sort.by(Sort.Direction.ASC, "createdAt");
            default:
                return Sort.by(Sort.Direction.DESC, "upvote");
        }
    }
}