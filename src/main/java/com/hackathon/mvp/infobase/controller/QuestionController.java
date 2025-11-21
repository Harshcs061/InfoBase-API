package com.hackathon.mvp.infobase.controller;

import com.hackathon.mvp.infobase.dto.AnswerRequest;
import com.hackathon.mvp.infobase.dto.QuestionRequest;
import com.hackathon.mvp.infobase.dto.SearchResponse;
import com.hackathon.mvp.infobase.model.Answer;
import com.hackathon.mvp.infobase.model.Question;
import com.hackathon.mvp.infobase.service.AnswerService;
import com.hackathon.mvp.infobase.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("api")
public class QuestionController {

    @Autowired
    private QuestionService questionService;

    @Autowired
    private AnswerService answerService;

    public QuestionController(){}

    @GetMapping("/test")
    public ResponseEntity<String> test(){
        return new ResponseEntity<>("Everything work fine", HttpStatus.OK);
    }


    // 1) User can submit question
    @PostMapping("/questions")
    public ResponseEntity<Question> submitQuestion(@RequestBody QuestionRequest req) {
        Question saved = questionService.submitQuestion(req);
        return ResponseEntity.ok(saved);
    }

    // list (optional) - simple pagination
    @GetMapping("/questions")
    public ResponseEntity<Page<Question>> listQuestions(@RequestParam(defaultValue = "0") int page,
                                                        @RequestParam(defaultValue = "10") int size) {
        return ResponseEntity.ok(questionService.listQuestions(PageRequest.of(page, size)));
    }

    // 2) User can search for answer (search across answers bodies)
    @GetMapping("/answers/search")
    public ResponseEntity<SearchResponse> searchAnswers(@RequestParam String q) {
        List<SearchResponse.AnswerResult> results = answerService.searchAnswers(q);
        SearchResponse resp = new SearchResponse(q, results);
        return ResponseEntity.ok(resp);
    }

    // 3) User can submit answer
    @PostMapping("/questions/{questionId}/answers")
    public ResponseEntity<Answer> submitAnswer(@PathVariable String questionId, @RequestBody AnswerRequest req) {
        Answer a = answerService.submitAnswer(UUID.fromString(questionId), req);
        return ResponseEntity.ok(a);
    }
}