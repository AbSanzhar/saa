package kz.saa.prototype.models.pojos.impl;

import kz.saa.prototype.models.entities.DissovetMemberEntity;
import kz.saa.prototype.models.pojos.Dissovet;
import lombok.Builder;

import java.util.Date;
import java.util.List;


@Builder
public class DefaultDissovet implements Dissovet {
    private Long disId;
    private String disInfo;
    private Date disStartDate;
    private Date disStopDate;
    private String ministryOrder;
    private Integer membersNum;
    private Long secretaryId;
    private List<DissovetMemberEntity> dissovetMembers;

    @Override
    public Long getDisId() {
        return disId;
    }

    @Override
    public String getDisInfo() {
        return disInfo;
    }

    @Override
    public Date getDisStartDate() {
        return disStartDate;
    }

    @Override
    public Date getDisStopDate() {
        return disStopDate;
    }

    @Override
    public String getMinistryOrder() {
        return ministryOrder;
    }

    @Override
    public Integer getMembersNum() {
        return membersNum;
    }

    public Long getSecretaryId() {
        return secretaryId;
    }

    @Override
    public List<DissovetMemberEntity> getDissovetMembers() {
        return dissovetMembers;
    }
}
