package com.hackathon.mvp.infobase.respository;

import com.hackathon.mvp.infobase.model.Question;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface QuestionRepository extends JpaRepository<Question, UUID> {
    Page<Question> findAll(Pageable pageable);

    // simple keyword search (title or body) using case-insensitive match
    @Query("select q from Question q where lower(q.title) like lower(concat('%', :term, '%')) or lower(q.body) like lower(concat('%', :term, '%'))")
    Page<Question> searchByTerm(String term, Pageable pageable);
}