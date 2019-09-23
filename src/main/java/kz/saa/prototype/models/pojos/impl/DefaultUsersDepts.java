package kz.saa.prototype.models.pojos.impl;

import kz.saa.prototype.models.entities.UserEntity;
import kz.saa.prototype.models.pojos.UsersDepts;
import lombok.Builder;

import java.util.List;


@Builder
public class DefaultUsersDepts implements UsersDepts {
    private Long userId;
    private Long deptId;
    private String userType;
    private String deptName;
    private String announcement;

    @Override
    public Long getUserId() {
        return userId;
    }

    @Override
    public Long getDeptId() {
        return deptId;
    }

    @Override
    public String getUserType() {
        return userType;
    }

    @Override
    public String getDeptName() {
        return deptName;
    }

    @Override
    public String getAnnouncement() {
        return announcement;
    }
}
