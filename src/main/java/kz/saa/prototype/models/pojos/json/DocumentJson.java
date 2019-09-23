package kz.saa.prototype.models.pojos.json;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
public class DocumentJson implements Serializable {

    @JsonProperty
    private Long docId;

    @JsonProperty
    private String docName;

    @JsonProperty
    private String docType;

    @JsonProperty
    private Date docDate;

    @JsonProperty
    private String docStatus;

    @JsonProperty
    private Long userId;
}
