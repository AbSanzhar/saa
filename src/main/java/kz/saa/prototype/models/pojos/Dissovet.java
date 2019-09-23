package kz.saa.prototype.models.pojos;

import kz.saa.prototype.models.entities.DissovetMemberEntity;

import java.util.Date;
import java.util.List;

public interface Dissovet {
    Long getDisId();
    String getDisInfo();
    Date getDisStartDate();
    Date getDisStopDate();
    String getMinistryOrder();
    Integer getMembersNum();
    Long getSecretaryId();
    List<DissovetMemberEntity> getDissovetMembers();
}
