package com.hackathon.mvp.infobase.respository;

import com.hackathon.mvp.infobase.model.Question;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface QuestionRepository extends JpaRepository<Question, Long> {

    @Query("SELECT q FROM Question q LEFT JOIN q.tags t WHERE (:tag IS NULL OR LOWER(t) = LOWER(:tag))")
    Page<Question> findByTag(@Param("tag") String tag, Pageable pageable);
}