package com.hackathon.mvp.infobase.service;

import com.hackathon.mvp.infobase.enums.ContentType;
import com.hackathon.mvp.infobase.model.Comment;
import com.hackathon.mvp.infobase.model.User;
import com.hackathon.mvp.infobase.respository.CommentRepository;
import com.hackathon.mvp.infobase.respository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
public class CommentService {

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private MentionService mentionService;

    @Transactional
    public Comment createComment(Long userId, String content) {
        User user = userRepository.findById(userId);

        Comment comment = new Comment();
        comment.setUser(user);
        comment.setContent(content);
        Comment saved = commentRepository.save(comment);

        mentionService.processMentions(
                ContentType.COMMENT,
                saved.getId(),
                content,
                user
        );

        log.info("Comment created: ID {} with mentions", saved.getId());
        return saved;
    }
}
