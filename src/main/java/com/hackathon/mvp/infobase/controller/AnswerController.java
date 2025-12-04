package com.hackathon.mvp.infobase.controller;

import com.hackathon.mvp.infobase.model.Answer;
import com.hackathon.mvp.infobase.service.AnswerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/answers")
@Slf4j
public class AnswerController {

    @Autowired
    private AnswerService answerService;

    @PostMapping
    public ResponseEntity<Answer> createAnswer(
            @RequestParam Long userId,
            @RequestParam Long questionId,
            @RequestParam String content) {

        Answer answer = answerService.createAnswer(userId, questionId, content);
        return ResponseEntity.status(HttpStatus.CREATED).body(answer);
    }
}
