package kz.saa.prototype.controllers;

import kz.saa.prototype.models.pojos.json.DocumentJson;
import kz.saa.prototype.services.DocumentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/docs")
public class DocumentController {

    @Autowired
    private DocumentService documentService;

    @GetMapping("/all")
    public ResponseEntity<?> showAll() {
        return ResponseEntity.ok(this.documentService.showAll());
    }

    @GetMapping("/{docId}")
    public ResponseEntity<?> showOne(@PathVariable Long docId) {
        return ResponseEntity.ok(this.documentService.showDoc(docId));
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<?> showByUser(@PathVariable Long userId) {
        return ResponseEntity.ok(this.documentService.showDocByUserId(userId));
    }

    @PostMapping("/add")
    public ResponseEntity<?> addDoc(@RequestBody DocumentJson documentJson) {
        this.documentService.addDocJson(documentJson);
        return ResponseEntity.ok("Doc added");
    }

    @PostMapping("/addFile")
    public ResponseEntity<?> addFile(Long docId, MultipartFile file) {
        this.documentService.addFile(docId, file);
        return ResponseEntity.ok("File uploaded");
    }

    @GetMapping("/uploads/{name:.+}")
    public ResponseEntity<?> getFile(@PathVariable("name") String name) {
        String contentType = null;

        if (name.contains(".jpg"))
            contentType = "image/jpeg";
        else if (name.contains(".pdf"))
            contentType = "application/pdf";
        else if (name.contains(".png"))
            contentType = "image/png";
        else
            contentType = "application/octet-stream";
        try {
            return ResponseEntity.ok()
                    .contentType(MediaType.parseMediaType(contentType))
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + name + "\"")
                    .body(documentService.getFile(name));
        } catch (IOException e) {
            return ResponseEntity.ok("Данного файла не существует");
        }
    }

    @PatchMapping("/{docId}")
    public ResponseEntity<?> updateDoc(@PathVariable Long docId, DocumentJson documentJson) {
        this.documentService.updateDoc(docId, documentJson);
        return ResponseEntity.ok("Doc updated!");
    }

    @DeleteMapping("/{docId}")
    public ResponseEntity<?> deleteDoc(@PathVariable Long docId) {
        this.documentService.deleteDoc(docId);
        return ResponseEntity.ok("Doc deleted!");
    }
}


