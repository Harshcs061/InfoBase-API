package com.hackathon.mvp.infobase.respository;

import com.hackathon.mvp.infobase.model.Tag;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface TagRepository extends JpaRepository<Tag, Long> {

    Optional<Tag> findByNameIgnoreCase(String name);

    List<Tag> findByNameInIgnoreCase(List<String> names);
}
