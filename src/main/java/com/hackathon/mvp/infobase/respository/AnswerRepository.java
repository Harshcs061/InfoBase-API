package com.hackathon.mvp.infobase.respository;

import com.hackathon.mvp.infobase.model.Answer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface AnswerRepository extends JpaRepository<Answer, Long> {
    
    @Query("SELECT a FROM Answer a WHERE a.question.id = :questionId")
    Page<Answer> findByQuestionId(@Param("questionId") Long questionId, Pageable pageable);
}