package kz.saa.prototype.models.pojos.impl;

import kz.saa.prototype.models.entities.RolesEntity;
import kz.saa.prototype.models.pojos.Login;
import lombok.Builder;

import java.util.List;

@Builder
public class DefaultLogin implements Login {
    private Long loginId;
    private String username;
    private String password;
    private Long userId;
    private List<RolesEntity> roles;

    @Override
    public Long getLoginId() {
        return loginId;
    }

    public void setLoginId(Long loginId) {
        this.loginId = loginId;
    }

    @Override
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    @Override
    public List<RolesEntity> getRoles() {
        return roles;
    }

    public void setRoles(List<RolesEntity> roles) {
        this.roles = roles;
    }
}
