package com.hackathon.mvp.infobase.mapper;
import com.hackathon.mvp.infobase.dto.UserDto;
import com.hackathon.mvp.infobase.model.User;
import org.springframework.stereotype.Component;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;


public class UserMapper {


    public static UserDto toDto(User user) {
        if (user == null) {
            return null;
        }

        return UserDto.builder()
                .id(user.getId())
                .name(user.getName())
                .email(user.getEmail())
                .project(user.getProject() != null ? user.getProject().getName() : null)
                .skills(user.getSkills() != null && !user.getSkills().isEmpty()
                        ? user.getSkills().stream()
                        .map(skill -> skill.getName())
                        .collect(Collectors.toList())
                        : Collections.emptyList())
                .build();
    }

//    public User toEntity(UserDto dto) {
//        if (dto == null) {
//            return null;
//        }
//
//        User user = User.builder()
//                .id(dto.getId())
//                .name(dto.getName())
//                .email(dto.getEmail())
//                .build();
//
//        // Note: Project and Skills need to be set separately
//        // as they require database lookups
//        return user;
//    }
//
//    public List<UserDto> toDtoList(List<User> users) {
//        if (users == null) {
//            return Collections.emptyList();
//        }
//
//        return users.stream()
//                .map(this::toDto)
//                .collect(Collectors.toList());
//    }
//
//    public void updateEntityFromDto(UserDto dto, User user) {
//        if (dto == null || user == null) {
//            return;
//        }
//
//        if (dto.getName() != null) {
//            user.setName(dto.getName());
//        }
//        if (dto.getEmail() != null) {
//            user.setEmail(dto.getEmail());
//        }
//
//        // Note: Project and Skills should be updated through service layer
//        // with proper repository lookups
    //}
}