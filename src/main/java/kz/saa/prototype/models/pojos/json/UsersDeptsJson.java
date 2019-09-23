package kz.saa.prototype.models.pojos.json;

import com.fasterxml.jackson.annotation.JsonProperty;
import kz.saa.prototype.models.entities.UsersDeptsId;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class UsersDeptsJson implements Serializable {

    @JsonProperty
    private Long userId;

    @JsonProperty
    private Long deptId;

    @JsonProperty
    private String userType;

}
