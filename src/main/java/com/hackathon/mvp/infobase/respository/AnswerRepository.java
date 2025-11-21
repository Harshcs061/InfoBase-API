package com.hackathon.mvp.infobase.respository;

import com.hackathon.mvp.infobase.model.Answer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface AnswerRepository extends JpaRepository<Answer, UUID> {
    List<Answer> findByQuestionId(UUID questionId);

    // search answers by body
    List<Answer> findByBodyContainingIgnoreCase(String term);
}