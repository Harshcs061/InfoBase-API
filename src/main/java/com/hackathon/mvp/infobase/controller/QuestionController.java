package com.hackathon.mvp.infobase.controller;

import com.hackathon.mvp.infobase.dto.CreateQuestionRequestDto;
import com.hackathon.mvp.infobase.dto.CreateQuestionResponseDto;
import com.hackathon.mvp.infobase.dto.QuestionDetailDto;
import com.hackathon.mvp.infobase.dto.QuestionListResponseDto;
import com.hackathon.mvp.infobase.service.QuestionService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
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
    public QuestionListResponseDto getAllQuestions(
            @RequestParam(name = "page", required = false, defaultValue = "1") int page,
            @RequestParam(name = "limit", required = false, defaultValue = "10") int limit,
            @RequestParam(name = "sortBy", required = false, defaultValue = "recent") String sortBy,
            @RequestParam(name = "tag", required = false) String tag
    ) {
        return questionService.getAllQuestions(page, limit, sortBy, tag);
    }

    // 2. Fetch Single Question
    @GetMapping("/{id}")
    public QuestionDetailDto getQuestionById(@PathVariable("id") Long id) {
        return questionService.getQuestionById(id);
    }

    // 3. Create New Question
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CreateQuestionResponseDto createQuestion(
            @Valid @RequestBody CreateQuestionRequestDto request
    ) {
        return questionService.createQuestion(request);
    }
}