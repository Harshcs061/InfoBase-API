package com.hackathon.mvp.infobase.respository;

import com.hackathon.mvp.infobase.model.Question;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QuestionRepository extends JpaRepository<Question, Long> {

    @EntityGraph(attributePaths = {"askedBy", "tags"})
    Page<Question> findDistinctByTags_NameIgnoreCase(String tag, Pageable pageable);

    @Override
    @EntityGraph(attributePaths = {"askedBy", "tags"})
    Page<Question> findAll(Pageable pageable);
}