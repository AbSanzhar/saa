package kz.saa.prototype.models.pojos;

import kz.saa.prototype.models.entities.DissovetMemberEntity;
import kz.saa.prototype.models.entities.RolesEntity;
import kz.saa.prototype.models.entities.UsersDeptsEntity;

import java.util.List;
import java.util.Set;

public interface User {
    Long getUserId();
    Long getLoginId();
    String getUsername();
    String getPassword();
    String getFirstName();
    String getLastName();
    String getEmail();
    String getDescription();
    List<RolesEntity> getRoles();
    List<UsersDepts> getUsersDepts();
}
