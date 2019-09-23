package kz.saa.prototype.models.pojos.impl;

import kz.saa.prototype.models.entities.UsersDeptsEntity;
import kz.saa.prototype.models.pojos.Department;
import lombok.Builder;

import java.util.Set;

@Builder
public class DefaultDepartment implements Department {

    private Long deptId;
    private String deptName;
    private String announcement;
    private Long headOfDept;
    private Set<UsersDeptsEntity> usersDeptsEntities;

    @Override
    public Long getDeptId() {
        return deptId;
    }

    @Override
    public String getDeptName() {
        return deptName;
    }

    @Override
    public String getAnnouncement() {
        return announcement;
    }

    @Override
    public Long getHeadOfDept() {
        return headOfDept;
    }

    @Override
    public Set<UsersDeptsEntity> getUsersDeptsEntities() {
        return usersDeptsEntities;
    }
}
