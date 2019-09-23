package kz.saa.prototype.models.pojos;

import java.util.Date;

public interface Document {
    Long getDocId();
    String getDocName();
    String getDocType();
    Date getDocDate();
    String getDocStatus();
    Long getUserId();
}
