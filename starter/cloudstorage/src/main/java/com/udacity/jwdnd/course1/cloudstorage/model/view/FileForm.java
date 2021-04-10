package com.udacity.jwdnd.course1.cloudstorage.model.view;

import lombok.Data;
import lombok.ToString;
import org.springframework.web.multipart.MultipartFile;

@Data
@ToString(includeFieldNames = true)
public class FileForm {
    private String fileId;
    private MultipartFile updatedFile;

    public FileForm() {
    }

    public FileForm(String fileId, MultipartFile updatedFile) {
        this.fileId = fileId;
        this.updatedFile = updatedFile;
    }
}
