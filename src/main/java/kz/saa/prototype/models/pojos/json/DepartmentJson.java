package kz.saa.prototype.models.pojos.json;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class DepartmentJson implements Serializable {

    @JsonProperty
    private String deptName;

    @JsonProperty
    private Long headOfDept;
}
