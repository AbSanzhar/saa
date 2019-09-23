package kz.saa.prototype.models.pojos.impl;

import kz.saa.prototype.models.pojos.Document;
import lombok.Builder;

import java.util.Date;

@Builder
public class DefaultDocument implements Document {

    private Long docId;
    private String docName;
    private String docType;
    private Date docDate;
    private String docStatus;
    private Long userId;

    @Override
    public Long getDocId() {
        return docId;
    }

    @Override
    public String getDocName() {
        return docName;
    }

    @Override
    public String getDocType() {
        return docType;
    }

    @Override
    public Date getDocDate() {
        return docDate;
    }

    @Override
    public String getDocStatus() {
        return docStatus;
    }

    @Override
    public Long getUserId() {
        return userId;
    }
}
