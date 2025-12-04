package com.hackathon.mvp.infobase.respository;

import com.hackathon.mvp.infobase.enums.ContentType;
import com.hackathon.mvp.infobase.model.Mention;
import com.hackathon.mvp.infobase.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MentionRepository extends JpaRepository<Mention, Long> {

    List<Mention> findByContentTypeAndContentId(ContentType contentType, Long contentId);

    List<Mention> findByTaggedUser(User user);

    List<Mention> findByMentionedBy(User user);

    boolean existsByContentTypeAndContentIdAndTaggedUser(
            ContentType contentType, Long contentId, User user);
}
