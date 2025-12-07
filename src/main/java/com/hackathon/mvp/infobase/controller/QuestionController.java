package com.hackathon.mvp.infobase.controller;

import com.hackathon.mvp.infobase.dto.*;
import com.hackathon.mvp.infobase.model.Question;
import com.hackathon.mvp.infobase.service.QuestionService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/questions")
public class QuestionController {

    private final QuestionService questionService;

    public QuestionController(QuestionService questionService) {
        this.questionService = questionService;
    }

    // 1. Get All Questions
    @GetMapping
    public ResponseEntity<QuestionListResponseDto> getAllQuestions(
            @RequestParam(name = "page", required = false, defaultValue = "1") int page,
            @RequestParam(name = "limit", required = false, defaultValue = "10") int limit,
            @RequestParam(name = "sortBy", required = false, defaultValue = "recent") String sortBy,
            @RequestParam(name = "tag", required = false) String tag
    ) {
        QuestionListResponseDto response = questionService.getAllQuestions(page, limit, sortBy, tag);
        return ResponseEntity.ok(response);
    }

    // 2. Fetch Single Question
    @GetMapping("/{id}")
    public ResponseEntity<QuestionDetailDto> getQuestionById(@PathVariable("id") Long id) {
        QuestionDetailDto response = questionService.getQuestionById(id);
        return ResponseEntity.ok(response);
    }

    // 3. Create New Question
    @PostMapping
    public ResponseEntity<CreateQuestionResponseDto> createQuestion(
            @Valid @RequestBody CreateQuestionRequestDto request
    ) {
        CreateQuestionResponseDto response = questionService.createQuestion(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

//    // 4. Update Existing Question
//    @PutMapping("/{id}")
//    public ResponseEntity<QuestionDetailDto> updateQuestion(@RequestBody UpdateQuestionRequestDto request) {
//        QuestionDetailDto response = questionService.updateQuestion(request);
//        return ResponseEntity.ok(response);
//    }
}