package kz.saa.prototype.models.pojos.json;

import com.fasterxml.jackson.annotation.JsonProperty;
import kz.saa.prototype.models.entities.RolesEntity;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

@Setter
@Getter
public class ExternalUserJson implements Serializable {

    @JsonProperty
    private String iin;

    @JsonProperty
    private String placeOfWork;

    @JsonProperty
    private Long userId;

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
}
