package kz.saa.prototype.services.impl;

import kz.saa.prototype.models.entities.DocumentEntity;
import kz.saa.prototype.models.pojos.Document;
import kz.saa.prototype.models.pojos.impl.DefaultDocument;
import kz.saa.prototype.models.pojos.json.DocumentJson;
import kz.saa.prototype.repositories.DocumentRepository;
import kz.saa.prototype.services.DocumentService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

@Slf4j
@Service
public class DefaultDocumentService implements DocumentService {

    @Autowired
    private DocumentRepository documentRepository;

    @Value("${upload.path}")
    private String uploadDir;

    @Override
    public String addDocJson(DocumentJson documentJson) {
        DocumentEntity documentEntity = new DocumentEntity();
        documentEntity.setDocName(documentJson.getDocName());
        documentEntity.setDocType(documentJson.getDocType());
        documentEntity.setDocDate(documentJson.getDocDate());
        documentEntity.setDocStatus(documentJson.getDocStatus());
        documentEntity.setUserId(documentJson.getUserId());
        this.documentRepository.save(documentEntity);
        return "Doc added!";
    }

    private DefaultDocument getDocument(DocumentEntity documentEntity) {
        return DefaultDocument.builder()
                .docId(documentEntity.getDocId())
                .docName(documentEntity.getDocName())
                .docType(documentEntity.getDocType())
                .docDate(documentEntity.getDocDate())
                .docStatus(documentEntity.getDocStatus())
                .userId(documentEntity.getUserId())
                .build();
    }

    private List<Document> getCollectDocument(List<DocumentEntity> documentEntities) {
        return documentEntities.stream().map(documentEntity ->
                getDocument(documentEntity))
                .collect(Collectors.toList());
    }

    @Override
    public List<Document> showAll() {
        List<DocumentEntity> documentEntities = new ArrayList<>();
        Iterable<DocumentEntity> iterable = this.documentRepository.findAll();
        iterable.forEach(documentEntities::add);
        return getCollectDocument(documentEntities);
    }

    @Override
    public List<Document> showDocByUserId(Long userId) {
        List<DocumentEntity> documentEntities = new ArrayList<>();
        Iterable<DocumentEntity> iterable = this.documentRepository.findByUserId(userId);
        iterable.forEach(documentEntities::add);
        return getCollectDocument(documentEntities);
    }

    @Override
    public DefaultDocument showDoc(Long docId) {
        DocumentEntity documentEntity = this.documentRepository.findById(docId).orElse(null);
        return getDocument(documentEntity);
    }

    @Override
    public String updateDoc(Long docId, DocumentJson documentJson) {
        DocumentEntity documentEntity = this.documentRepository.findById(docId).orElse(null);
        if (Objects.nonNull(documentEntity)) {
            if (documentJson.getDocName() != null)
                documentEntity.setDocName(documentJson.getDocName());
            if (documentJson.getDocType() != null)
                documentEntity.setDocType(documentJson.getDocType());
            if (documentJson.getDocDate() != null)
                documentEntity.setDocDate(documentJson.getDocDate());
            if (documentJson.getDocStatus() != null)
                documentEntity.setDocStatus(documentJson.getDocStatus());
            if (documentJson.getUserId() != null)
                documentEntity.setUserId(documentJson.getUserId());
            this.documentRepository.save(documentEntity);
            log.info("Updated doc with id: {} . {}", documentEntity.getDocId(), new Date());
            return "Updated successfully";
        } else {

        }
        return "Something goes wrong";
    }

    @Override
    public String deleteDoc(Long docId) {
        DocumentEntity documentEntity = this.documentRepository.findById(docId).orElse(null);
        if (Objects.nonNull(documentEntity)) {
            log.info("Deleted doc with id: {} . {}", docId, new Date());
            this.documentRepository.deleteById(docId);
            return "Doc deleted";
        }
        else {
            return "Cannot find doc with id";
        }
    }

    @Override
    public String addFile(Long docId, MultipartFile file) {
        DocumentEntity documentEntity = this.documentRepository.getOne(docId);

        if (documentEntity == null) {
            return "Cannot find doc with id";
        }
        try {
            String filename = UUID.randomUUID() + "." + FilenameUtils.getExtension(file.getOriginalFilename());
            File transferFile = new File(uploadDir + filename);
            file.transferTo(transferFile);

            String photosUrlList = documentEntity.getDocFile();
            if (photosUrlList == null)
                photosUrlList = "";
            if (photosUrlList.equals(""))
                documentEntity.setDocFile(filename);
            else
                documentEntity.setDocFile(photosUrlList + "," + filename);
            this.documentRepository.save(documentEntity);
            return ServletUriComponentsBuilder.fromCurrentContextPath()
                    .path("/product/uploads/")
                    .path(filename)
                    .toUriString();

        } catch (IOException e) {
            return "Error";
        }
    }

    @Override
    public byte[] getFile(String docName) throws IOException {
        File file = new File(uploadDir + docName);
        FileInputStream fileInputStream = new FileInputStream(file);
        return IOUtils.toByteArray(fileInputStream);
    }
}
