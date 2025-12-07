package com.hackathon.mvp.infobase.service;

import org.springframework.stereotype.Service;

@Service
public interface VoteService {

    boolean voteQuestion(Long userId,Long votingId,String action);

    boolean voteAnswer(Long userId, Long votingId, String action);
}
