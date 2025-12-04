package com.hackathon.mvp.infobase.service;

import com.hackathon.mvp.infobase.enums.ContentType;
import com.hackathon.mvp.infobase.model.Mention;
import com.hackathon.mvp.infobase.model.Notification;
import com.hackathon.mvp.infobase.model.User;
import com.hackathon.mvp.infobase.respository.MentionRepository;
import com.hackathon.mvp.infobase.respository.NotificationRepository;
import com.hackathon.mvp.infobase.respository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
@Slf4j
public class MentionService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private MentionRepository mentionRepository;

    @Autowired
    private NotificationRepository notificationRepository;

    private static final Pattern MENTION_PATTERN = Pattern.compile("@(\\w+)");

    /**
     * Extract mentioned usernames from any text content
     */
    public Set<String> extractMentionedUsernames(String content) {
        Set<String> mentions = new HashSet<>();
        Matcher matcher = MENTION_PATTERN.matcher(content);

        while (matcher.find()) {
            mentions.add(matcher.group(1));
        }

        return mentions;
    }

    /**
     * Process mentions for ANY type of content
     * This method works for Questions, Answers, Comments, Posts, etc.
     */
    @Transactional
    public void processMentions(
            ContentType contentType,
            Long contentId,
            String content,
            User contentAuthor) {

        Set<String> mentionedUsernames = extractMentionedUsernames(content);
        Set<Long> processedUserIds = new HashSet<>();

        for (String username : mentionedUsernames) {
            // Skip self-mentions
            if (username.equalsIgnoreCase(contentAuthor.getName())) {
                log.debug("Skipping self-mention: {}", username);
                continue;
            }

            // Find the user
            Optional<User> taggedUserOpt = userRepository.findByEmail(username);
            if (taggedUserOpt.isEmpty()) {
                log.debug("User not found: {}", username);
                continue;
            }

            User taggedUser = taggedUserOpt.get();

            // Prevent duplicate mentions
            if (processedUserIds.contains(taggedUser.getId())) {
                log.debug("User already mentioned: {}", username);
                continue;
            }

            // Check if mention already exists
            if (mentionRepository.existsByContentTypeAndContentIdAndTaggedUser(
                    contentType, contentId, taggedUser)) {
                log.debug("Mention already exists for user: {}", username);
                continue;
            }

            // Create generic Mention record
            Mention mention = new Mention();
            mention.setContentType(contentType);
            mention.setContentId(contentId);
            mention.setTaggedUser(taggedUser);
            mention.setMentionedBy(contentAuthor);
            Mention savedMention = mentionRepository.save(mention);

            // Create Notification
            Notification notification = new Notification();
            notification.setUser(taggedUser);
            notification.setMention(savedMention);
            notificationRepository.save(notification);

            processedUserIds.add(taggedUser.getId());
            log.info("Mention created: {} mentioned in {} #{}",
                    username, contentType, contentId);
        }
    }

    /**
     * Get all mentions in a specific content (Question, Answer, Comment, etc.)
     */
    public List<Mention> getMentionsInContent(ContentType contentType, Long contentId) {
        return mentionRepository.findByContentTypeAndContentId(contentType, contentId);
    }

    /**
     * Get all mentions of a specific user
     */
    public List<Mention> getMentionsOfUser(User user) {
        return mentionRepository.findByTaggedUser(user);
    }
}