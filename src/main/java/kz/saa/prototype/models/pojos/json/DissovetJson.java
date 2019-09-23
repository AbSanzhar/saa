package kz.saa.prototype.models.pojos.json;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
public class DissovetJson implements Serializable {

    @JsonProperty
    private Long disId;

    @JsonProperty
    private String disInfo;

    @JsonProperty
    private Date disStartDate;

    @JsonProperty
    private Date disStopDate;

    @JsonProperty
    private String ministryOrder;

    @JsonProperty
    private Integer membersNum;

    @JsonProperty
    private Long secretaryId;
}
