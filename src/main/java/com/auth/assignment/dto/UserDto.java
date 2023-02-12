package com.auth.assignment.dto;

import com.auth.assignment.model.Role;
import com.auth.assignment.model.User;
import lombok.Builder;
import lombok.Data;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Data
@Builder
public class UserDto {

    private Long id;
    private String userName;
    private String email;
    private Boolean isActive;
    private Set<RoleDto> roleDtoSet;

    public static UserDto create(User user){
        return UserDto.builder()
                .id(user.getId())
                .userName(user.getName())
                .email(user.getEmail())
                .isActive(user.getIsActive())
                .roleDtoSet(user.getRoles().stream().map(RoleDto::create).collect(Collectors.toSet()))
                .build();
    }

}
