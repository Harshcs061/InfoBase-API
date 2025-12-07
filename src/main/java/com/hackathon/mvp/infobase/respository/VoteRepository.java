package com.hackathon.mvp.infobase.respository;

import com.hackathon.mvp.infobase.model.Vote;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface VoteRepository extends JpaRepository<Vote,Long> {

    @Query("SELECT v FROM Vote v WHERE v.votedOn = 'QUESTION' AND v.userId = :userId AND v.votingId = :contentId ")
    Vote findVoteForQuestion(
            @Param("userId") Long userId,
            @Param("contentId") Long contentId
    );

    @Query("SELECT v FROM Vote v WHERE v.votedOn = 'ANSWER' AND v.userId = :userId AND v.votingId = :contentId ")
    Vote findVoteForAnswer(
            @Param("userId") Long userId,
            @Param("contentId") Long contentId
    );
}
