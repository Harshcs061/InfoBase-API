package com.hackathon.mvp.infobase.controller;


import com.hackathon.mvp.infobase.dto.VoteRequest;
import com.hackathon.mvp.infobase.service.VoteService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class VoteController {

    private final VoteService voteService;

    public VoteController(VoteService voteService) {
        this.voteService = voteService;
    }

    @PostMapping("/question/vote")
    public ResponseEntity<Boolean> voteQuestion(@RequestBody VoteRequest request) {

        if (request.getAction() == null || request.getAction().trim().isEmpty()) {
            throw new IllegalArgumentException("Action cannot be empty");
        }

        String normalizedAction = request.getAction().trim().toLowerCase();

        boolean result = voteService.voteQuestion(request.getUserId(),request.getVotingId(), request.getAction());

        return ResponseEntity.ok(result);
    }


    @PostMapping("/answer/vote")
    public ResponseEntity<Boolean> voteAnswer(@RequestBody VoteRequest request) {

        if (request.getAction() == null || request.getAction().trim().isEmpty()) {
            throw new IllegalArgumentException("Action cannot be empty");
        }

        String normalizedAction = request.getAction().trim().toLowerCase();

        boolean result = voteService.voteAnswer(request.getUserId(),request.getVotingId(), request.getAction());

        return ResponseEntity.ok(result);
    }

}