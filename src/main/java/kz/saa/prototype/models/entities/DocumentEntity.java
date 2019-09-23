package kz.saa.prototype.models.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@Entity
@Table(name = "documents")
public class DocumentEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "doc_id")
    private Long docId;

    @Column(name = "doc_name")
    private String docName;

    @Column(name = "doc_type")
    private String docType;

    @Column(name = "doc_date")
    private Date docDate;

    @Column(name = "doc_status")
    private String docStatus;

    @Column(name = "doc_file")
    private String docFile;

    @Column(name = "user_id")
    private Long userId;
}
