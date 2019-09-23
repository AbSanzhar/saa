package kz.saa.prototype.models.pojos;

import java.util.Date;

public interface DissovetMember {

    Long getDisId();
    String getDisInfo();
    String getMemberType();
    String getDisPosition();
    Date getDisStartDate();
    Date getDisStopDate();
    Long getUserId();
    String getMinistryOrder();
    Integer getMembersNum();
    Long getSecretaryId();
    String getSecretaryFirstName();
    String getSecretaryLastName();

}
