package org.zotov.hotel_aggregator.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Table("roles")
public class Role {
    @Id
    private Long id;

    @Column("role_name")
    private String roleName;

    @Column("role_level")
    private Integer roleLevel;

    public Role() {
    }

    public Role(Long id, String roleName, Integer roleLevel) {
        this.id = id;
        this.roleName = roleName;
        this.roleLevel = roleLevel;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public Integer getRoleLevel() {
        return roleLevel;
    }

    public void setRoleLevel(Integer roleLevel) {
        this.roleLevel = roleLevel;
    }
}
