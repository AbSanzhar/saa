package kz.saa.prototype.models.pojos.impl;

import kz.saa.prototype.models.entities.DissovetMemberEntity;
import kz.saa.prototype.models.entities.RolesEntity;
import kz.saa.prototype.models.entities.UsersDeptsEntity;
import kz.saa.prototype.models.pojos.User;
import kz.saa.prototype.models.pojos.UsersDepts;
import lombok.Builder;

import java.util.List;
import java.util.Set;

@Builder
public class DefaultUser implements User {
    private Long userId;
    private Long loginId;
    private String username;
    private String password;
    private String firstName;
    private String lastName;
    private String email;
    private String description;
    private List<RolesEntity> roles;
    private List<UsersDepts> usersDepts;

    @Override
    public Long getUserId() {
        return userId;
    }

    @Override
    public Long getLoginId() {
        return loginId;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getFirstName() {
        return firstName;
    }

    @Override
    public List<RolesEntity> getRoles() {
        return roles;
    }

    @Override
    public String getLastName() {
        return lastName;
    }

    @Override
    public String getEmail() {
        return email;
    }

    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public List<UsersDepts> getUsersDepts() {
        return usersDepts;
    }

}
