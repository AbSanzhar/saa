package kz.saa.prototype.models.pojos.impl;

import kz.saa.prototype.models.pojos.DissovetMember;
import lombok.Builder;

import java.util.Date;

@Builder
public class DefaultDissovetMember implements DissovetMember {
    private Long disId;
    private String disInfo;
    private String memberType;
    private String disPosition;
    private Date disStartDate;
    private Date disStopDate;
    private Long userId;
    private String ministryOrder;
    private Integer membersNum;
    private Long secretaryId;
    private String secretaryFirstName;
    private String secretaryLastName;

    @Override
    public Long getDisId() {
        return disId;
    }

    @Override
    public String getDisInfo() {
        return disInfo;
    }

    @Override
    public String getMemberType() {
        return memberType;
    }

    @Override
    public String getDisPosition() {
        return disPosition;
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

    @Override
    public Long getSecretaryId() {
        return secretaryId;
    }

    @Override
    public Long getUserId() {
        return userId;
    }

    @Override
    public String getSecretaryFirstName() {
        return secretaryFirstName;
    }

    @Override
    public String getSecretaryLastName() {
        return secretaryLastName;
    }
}
