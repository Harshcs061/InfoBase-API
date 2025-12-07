package com.hackathon.mvp.infobase.controller;

import com.hackathon.mvp.infobase.dto.*;
import com.hackathon.mvp.infobase.service.AnswerService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@Slf4j
public class AnswerController {

    @Autowired
    private AnswerService answerService;

    // 2.1 Get Answers for Question
    @GetMapping("/questions/{questionId}/answers")
    public ResponseEntity<AnswerListResponseDto> getAnswersForQuestion(
            @PathVariable Long questionId,
            @RequestParam(name = "sortBy", required = false, defaultValue = "votes") String sortBy,
            @RequestParam(name = "page", required = false, defaultValue = "1") int page,
            @RequestParam(name = "limit", required = false, defaultValue = "20") int limit) {
        
        AnswerListResponseDto response = answerService.getAnswersForQuestion(questionId, sortBy, page, limit);
        return ResponseEntity.ok(response);
    }

    // 2.2 Get Answer by ID
    @GetMapping("/answers/{id}")
    public ResponseEntity<AnswerResponseDto> getAnswerById(@PathVariable Long id) {
        AnswerResponseDto response = answerService.getAnswerById(id);
        return ResponseEntity.ok(response);
    }

    // 2.3 Create Answer
    @PostMapping("/questions/{questionId}/answers")
    public ResponseEntity<AnswerResponseDto> createAnswer(
            @PathVariable Long questionId,
            @Valid @RequestBody CreateAnswerRequestDto request) {
        
        // Get authenticated user email from security context
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userEmail = authentication.getName();
        
        AnswerResponseDto response = answerService.createAnswer(questionId, userEmail, request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    // 2.4 Update Answer
    @PutMapping("/answers/{id}")
    public ResponseEntity<AnswerResponseDto> updateAnswer(
            @PathVariable Long id,
            @Valid @RequestBody UpdateAnswerRequestDto request) {
        
        // Get authenticated user email from security context
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userEmail = authentication.getName();
        
        AnswerResponseDto response = answerService.updateAnswer(id, userEmail, request);
        return ResponseEntity.ok(response);
    }

    // 2.5 Delete Answer
    @DeleteMapping("/answers/{id}")
    public ResponseEntity<DeleteResponseDto> deleteAnswer(@PathVariable Long id) {
        
        // Get authenticated user email from security context
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userEmail = authentication.getName();
        
        DeleteResponseDto response = answerService.deleteAnswer(id, userEmail);
        return ResponseEntity.ok(response);
    }
}