package kz.saa.prototype.models.pojos.json;

import com.fasterxml.jackson.annotation.JsonProperty;
import kz.saa.prototype.models.entities.RolesEntity;
import kz.saa.prototype.models.entities.UsersDeptsEntity;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

@Getter
@Setter
public class UserJson implements Serializable {

    @JsonProperty
    private String username;

    @JsonProperty
    private String password;

    @JsonProperty
    private String firstName;

    @JsonProperty
    private String lastName;

    @JsonProperty
    private String email;

    @JsonProperty
    private String description;

    @JsonProperty
    private List<RolesEntity> roles;

    @JsonProperty
    private Set<UsersDeptsJson> usersDeptsJsons;

}
