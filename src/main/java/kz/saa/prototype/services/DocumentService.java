package kz.saa.prototype.services;

import kz.saa.prototype.models.pojos.Document;
import kz.saa.prototype.models.pojos.impl.DefaultDocument;
import kz.saa.prototype.models.pojos.json.DocumentJson;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface DocumentService {

    String addDocJson(DocumentJson documentJson);

    List<Document> showAll();

    List<Document> showDocByUserId(Long userId);

    DefaultDocument showDoc(Long docId);

    String updateDoc(Long docId, DocumentJson documentJson);

    String deleteDoc(Long docId);

    String addFile(Long docId, MultipartFile file);

    byte[] getFile(String docName) throws IOException;


}
