package com.auth.assignment.model;

import com.auth.assignment.dto.RoleDto;
import com.auth.assignment.util.CommonUtils;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "roles")
@Entity
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @NotNull(message = "Rolename is required")
    @Column(name = "role_name")
    private String roleName;

    public Role(String roleName) {
        this.roleName = roleName;
    }

}
