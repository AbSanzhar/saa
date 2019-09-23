package kz.saa.prototype.models.pojos.json;

import com.fasterxml.jackson.annotation.JsonProperty;
import kz.saa.prototype.models.entities.RolesEntity;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

@Getter
@Setter
public class DissovetMemberJson implements Serializable {

    @JsonProperty
    private Long memberId;

    @JsonProperty
    private String firstName;

    @JsonProperty
    private String lastName;

    @JsonProperty
    private String memberType;

    @JsonProperty
    private String academicDegree;

    @JsonProperty
    private String specCode;

    @JsonProperty
    private String disSpecCode;

    @JsonProperty
    private String workPlace;

    @JsonProperty
    private String disPosition;

    @JsonProperty
    private Long userId;

    @JsonProperty
    private Long disId;


}
