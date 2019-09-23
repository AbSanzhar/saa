package kz.saa.prototype.models.pojos;

import kz.saa.prototype.models.entities.UsersDeptsEntity;

import java.util.Set;

public interface Department {
    Long getDeptId();
    String getDeptName();
    String getAnnouncement();
    Long getHeadOfDept();
    Set<UsersDeptsEntity> getUsersDeptsEntities();
}
