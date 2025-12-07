package com.hackathon.mvp.infobase.serviceImpl;

import com.hackathon.mvp.infobase.enums.ContentType;
import com.hackathon.mvp.infobase.enums.VoteType;
import com.hackathon.mvp.infobase.model.Answer;
import com.hackathon.mvp.infobase.model.Question;
import com.hackathon.mvp.infobase.model.Vote;
import com.hackathon.mvp.infobase.respository.AnswerRepository;
import com.hackathon.mvp.infobase.respository.QuestionRepository;
import com.hackathon.mvp.infobase.respository.VoteRepository;
import com.hackathon.mvp.infobase.service.VoteService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class VoteServiceImpl implements VoteService {

    private final VoteRepository voteRepository;
    private final QuestionRepository questionRepository;
    private final AnswerRepository answerRepository;

    public VoteServiceImpl(VoteRepository voteRepository,
                           QuestionRepository questionRepository,
                           AnswerRepository answerRepository) {
        this.voteRepository = voteRepository;
        this.questionRepository = questionRepository;
        this.answerRepository = answerRepository;
    }

    // ==================== QUESTION VOTE ====================

    @Override
    @Transactional
    public boolean voteQuestion(Long userId, Long votingId, String actionRaw) {

        String action = actionRaw == null ? "" : actionRaw.trim().toLowerCase();
        if (!action.equals("upvote") && !action.equals("downvote")) {
            throw new IllegalArgumentException("Invalid action: " + actionRaw);
        }

        Question ques = questionRepository.findById(votingId)
                .orElseThrow(() -> new IllegalArgumentException("Question not found"));

        int up = ques.getUpvote();
        int down = ques.getDownvote();

        Vote existingVote = voteRepository.findVoteForQuestion(userId, votingId);
        VoteType newType = action.equals("upvote") ? VoteType.UPVOTE : VoteType.DOWNVOTE;

        if (existingVote != null) {
            VoteType previous = existingVote.getVoteType();

            // if same vote again → do nothing
            if (previous == newType) {
                return false;
            }

            // remove effect of previous vote
            if (previous == VoteType.UPVOTE) {
                up = up - 1;
            } else if (previous == VoteType.DOWNVOTE) {
                down =  down - 1;
            }

            // apply new vote
            if (newType == VoteType.UPVOTE) {
                up++;
            } else {
                down++;
            }

            existingVote.setVoteType(newType);
            voteRepository.save(existingVote);

        } else {
            // first time voting
            Vote vote = Vote.builder()
                    .userId(userId)
                    .votingId(votingId)
                    .votedOn(ContentType.QUESTION)
                    .voteType(newType)
                    .build();

            voteRepository.save(vote);

            if (newType == VoteType.UPVOTE) {
                up++;
            } else {
                down++;
            }
        }

        // IMPORTANT: always update counts and save the question
        ques.setUpvote(up);
        ques.setDownvote(down);
        questionRepository.save(ques);

        return true;
    }

    // ==================== ANSWER VOTE ====================

    @Override
    @Transactional
    public boolean voteAnswer(Long userId, Long votingId, String actionRaw) {

        String action = actionRaw == null ? "" : actionRaw.trim().toLowerCase();
        if (!action.equals("upvote") && !action.equals("downvote")) {
            throw new IllegalArgumentException("Invalid action: " + actionRaw);
        }

        Answer ans = answerRepository.findById(votingId)
                .orElseThrow(() -> new IllegalArgumentException("Answer not found"));

        int up = ans.getUpvote();
        int down = ans.getDownvote();

        // ⚠️ assuming you have this method in repo; if not, create it
        Vote existingVote = voteRepository.findVoteForAnswer(userId, votingId);
        VoteType newType = action.equals("upvote") ? VoteType.UPVOTE : VoteType.DOWNVOTE;

        if (existingVote != null) {
            VoteType previous = existingVote.getVoteType();

            // if same vote again → do nothing
            if (previous == newType) {
                return false;
            }

            // remove effect of previous vote
            if (previous == VoteType.UPVOTE) {
                up =  up - 1;
            } else if (previous == VoteType.DOWNVOTE) {
                down = down - 1;
            }

            // apply new vote
            if (newType == VoteType.UPVOTE) {
                up++;
            } else {
                down++;
            }

            existingVote.setVoteType(newType);
            voteRepository.save(existingVote);

        } else {
            // first time voting
            Vote vote = Vote.builder()
                    .userId(userId)
                    .votingId(votingId)
                    .votedOn(ContentType.ANSWER)   // ✅ ANSWER here
                    .voteType(newType)
                    .build();

            voteRepository.save(vote);

            if (newType == VoteType.UPVOTE) {
                up++;
            } else {
                down++;
            }
        }

        // IMPORTANT: always update counts and save the answer
        ans.setUpvote(up);
        ans.setDownvote(down);
        answerRepository.save(ans);

        return true;
    }
}
