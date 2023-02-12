package com.auth.assignment.dto;

import com.auth.assignment.model.Role;
import lombok.Builder;
import lombok.Data;

import java.util.HashSet;
import java.util.Set;

@Data
@Builder
public class RoleDto {

    private String roleName;

    public static RoleDto create(Role role){
        return RoleDto.builder()
                .roleName(role.getRoleName()).build();
    }

}
