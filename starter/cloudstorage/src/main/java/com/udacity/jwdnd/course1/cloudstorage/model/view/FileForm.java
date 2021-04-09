package com.udacity.jwdnd.course1.cloudstorage.model.view;

import lombok.Data;
import lombok.ToString;
import org.springframework.web.multipart.MultipartFile;

@Data
@ToString(includeFieldNames = true)
public class FileForm {
    private String fileId;
    private MultipartFile updatedFile;

    public FileForm(MultipartFile file){
        this.updatedFile = file;
    }

    public FileForm() {
    }

    public FileForm(String fileId, MultipartFile updatedFile) {
        this.fileId = fileId;
        this.updatedFile = updatedFile;
    }

//    public MultipartFile getFile() {
//        return updatedFile;
//    }
//
//    public void setFile(MultipartFile file) {
//        this.updatedFile = file;
//    }
//
//    public String getFileId() {
//        return fileId;
//    }
//
//    public void setFileId(String fileId) {
//        this.fileId = fileId;
//    }
}
