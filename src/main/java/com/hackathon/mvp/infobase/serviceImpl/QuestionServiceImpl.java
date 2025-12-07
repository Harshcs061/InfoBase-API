package com.hackathon.mvp.infobase.serviceImpl;

import com.hackathon.mvp.infobase.dto.*;
import com.hackathon.mvp.infobase.enums.QuestionVisibility;
import com.hackathon.mvp.infobase.mapper.QuestionMapper;
import com.hackathon.mvp.infobase.model.Project;
import com.hackathon.mvp.infobase.model.Question;
import com.hackathon.mvp.infobase.model.Tag;
import com.hackathon.mvp.infobase.model.User;
import com.hackathon.mvp.infobase.respository.ProjectRepository;
import com.hackathon.mvp.infobase.respository.QuestionRepository;
import com.hackathon.mvp.infobase.respository.TagRepository;
import com.hackathon.mvp.infobase.respository.UserRepository;
import com.hackathon.mvp.infobase.service.MentionService;
import com.hackathon.mvp.infobase.service.QuestionService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


@Service
@Transactional
public class QuestionServiceImpl implements QuestionService {

    private final QuestionRepository questionRepository;
    private final UserRepository userRepository;
    private final TagRepository tagRepository;
    private final ProjectRepository projectRepository;

    @Autowired
    private MentionService mentionService;

    public QuestionServiceImpl(QuestionRepository questionRepository,
                               UserRepository userRepository,
                               TagRepository tagRepository,
                               ProjectRepository projectRepository) {
        this.questionRepository = questionRepository;
        this.userRepository = userRepository;
        this.tagRepository = tagRepository;
        this.projectRepository = projectRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public QuestionListResponseDto getAllQuestions(int page, int limit, String sortBy, String tag) {
        int safePage = Math.max(page, 1);
        int safeLimit = Math.max(limit, 1);

        Sort sort = buildSort(sortBy);
        Pageable pageable = PageRequest.of(safePage - 1, safeLimit, sort);

        String normalizedTag = (tag != null) ? tag.trim() : null;
        Page<Question> questionPage;

        if (normalizedTag != null && !normalizedTag.isEmpty()) {
            questionPage = questionRepository.findDistinctByTags_NameIgnoreCase(normalizedTag, pageable);
        } else {
            questionPage = questionRepository.findAll(pageable);
        }

        List<QuestionDetailDto> items = questionPage
                .getContent()
                .stream()
                .map(QuestionMapper::toDetailDto)
                .toList();

        return QuestionListResponseDto.builder()
                .total(questionPage.getTotalElements())
                .page(safePage)
                .limit(safeLimit)
                .questions(items)
                .build();
    }

    private Sort buildSort(String sortBy) {
        if (sortBy == null) {
            return Sort.by(Sort.Direction.DESC, "createdAt"); // default: recent
        }

        switch (sortBy.toLowerCase()) {
            case "votes":
                return Sort.by(Sort.Direction.DESC, "votes");
            case "recent":
                return Sort.by(Sort.Direction.DESC, "createdAt");
            default:
                return Sort.by(Sort.Direction.DESC, "createdAt");
        }
    }


    @Override
    @Transactional(readOnly = true)
    public QuestionDetailDto getQuestionById(Long id) {
        Question question = questionRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Question not found with id: " + id));

        return QuestionMapper.toDetailDto(question);
    }

    @Override
    public CreateQuestionResponseDto createQuestion(CreateQuestionRequestDto request) {
        if (request.getTitle() == null || request.getTitle().isBlank()) {
            throw new IllegalArgumentException("Title is required");
        }
        if (request.getDescription() == null || request.getDescription().isBlank()) {
            throw new IllegalArgumentException("Description is required");
        }
        if (request.getTags() != null && request.getTags().size() > 5) {
            throw new IllegalArgumentException("Maximum 5 tags allowed");
        }

        QuestionVisibility visibility = QuestionVisibility.PUBLIC;
        if (request.getVisibility() != null && !request.getVisibility().isBlank()) {
            if ("TEAM".equalsIgnoreCase(request.getVisibility())) {
                visibility = QuestionVisibility.TEAM;
            } else if("PUBLIC".equalsIgnoreCase(request.getVisibility())) {
                visibility = QuestionVisibility.PUBLIC;
            }else{
                visibility = QuestionVisibility.CONFIDENTIAL;
            }
        }
        

        User askedBy = userRepository.findById(request.getAskedBy())
                .orElseThrow(() -> new EntityNotFoundException(
                        "User not found with id: " + request.getAskedBy()
                ));


        Set<Tag> tags = resolveTags(request.getTags());
        Project project = projectRepository.findById(request.getRelated_project())
                .orElseThrow(() -> new IllegalArgumentException("Project Id is invalid"));

        Question question = Question.builder()
                .title(request.getTitle())
                .description(request.getDescription())
                .tags(tags)
                .askedBy(askedBy)
                .visibility(visibility)
                .votes(0)
                .answersCount(0)
                .views(0)
                .createdAt(LocalDateTime.now())
                .project(project)
                .build();

        Question saved = questionRepository.save(question);

        return CreateQuestionResponseDto.builder()
                .success(true)
                .message("Question created successfully")
                .question(QuestionMapper.toDetailForCreateDto(saved))
                .build();

    }
//
//    @Override
//    public QuestionDetailDto updateQuestion(UpdateQuestionRequestDto request) {
//        if (request.getId() == null) {
//            throw new IllegalArgumentException("Question id is required for update");
//        }
//
//        Question question = questionRepository.findById(request.getId())
//                .orElseThrow(() -> new EntityNotFoundException("Question not found with id: " + request.getId()));
//
//        boolean changed = false;
//
//        // 2. Update title only if provided (non-null & non-blank)
//        if (request.getTitle() != null) {
//            String newTitle = request.getTitle().trim();
//            if (!newTitle.isBlank() && !newTitle.equals(question.getTitle())) {
//                question.setTitle(newTitle);
//                changed = true;
//            }
//        }
//
//        if (request.getDescription() != null) {
//            String newDescription = request.getDescription().trim();
//            if (!newDescription.isBlank() && !newDescription.equals(question.getDescription())) {
//                question.setDescription(newDescription);
//                changed = true;
//            }
//        }
//
//
//        if (request.getVisibility() != null && !request.getVisibility().isBlank()) {
//            QuestionVisibility newVisibility;
//            if (request.getVisibility().equalsIgnoreCase("organization")) {
//                newVisibility = QuestionVisibility.;
//            } else {
//                newVisibility = QuestionVisibility.PUBLIC;
//            }
//
//            if (question.getVisibility() != newVisibility) {
//                question.setVisibility(newVisibility);
//                changed = true;
//            }
//        }
//
//
//        if (changed) {
//            question = questionRepository.save(question);
//        }
//
//        QuestionDetailDto dto = QuestionMapper.toDetailDto(question);
//        return dto;
//    }


    private Set<Tag> resolveTags(List<Long> tagIds) {
        if (tagIds == null || tagIds.isEmpty()) {
            return Set.of();
        }

        Set<Tag> result = new HashSet<>();

        for (Long id : tagIds) {
            tagRepository.findById(id).ifPresent(result::add);
        }

        return result;
    }
}