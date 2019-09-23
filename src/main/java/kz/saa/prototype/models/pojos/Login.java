package kz.saa.prototype.models.pojos;

import kz.saa.prototype.models.entities.RolesEntity;

import java.util.List;

public interface Login {
    Long getLoginId();
    String getUsername();
    String getPassword();
    Long getUserId();
    List<RolesEntity> getRoles();
}
