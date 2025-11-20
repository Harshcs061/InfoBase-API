package com.hackathon.mvp.infobase.respository;

import com.hackathon.mvp.infobase.model.Answer;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.UUID;

public interface AnswerRepository extends JpaRepository<Answer, UUID> {
    List<Answer> findByQuestionId(UUID questionId);

    // search answers by body
    List<Answer> findByBodyContainingIgnoreCase(String term);
}