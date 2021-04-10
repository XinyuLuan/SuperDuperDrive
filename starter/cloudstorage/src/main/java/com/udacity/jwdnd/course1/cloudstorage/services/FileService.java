package com.udacity.jwdnd.course1.cloudstorage.services;

import com.udacity.jwdnd.course1.cloudstorage.mapper.FileMapper;
import com.udacity.jwdnd.course1.cloudstorage.model.File;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Slf4j
@Service
public class FileService {
    @Autowired
    private FileMapper fileMapper;

    public File findFile(String filename){
        return fileMapper.getFile(filename);
    }

    public int insertFile(MultipartFile file, int userId) throws IOException {
        File newFile = new File(file.getOriginalFilename(), file.getContentType(), Long.toString(file.getSize()), userId, file.getBytes());
        return fileMapper.insert(newFile);
    }

    public int updateFile(MultipartFile file, int userId) throws IOException {
        File newFile = new File(file.getOriginalFilename(), file.getContentType(), Long.toString(file.getSize()), userId, file.getBytes());
        return fileMapper.update(newFile);
    }

    public boolean hasDuplication(int userId, String filename){
        File file = fileMapper.hasDuplicateFile(userId, filename);
        return file != null;
    }

    public int delete(Integer fileId){
        return fileMapper.delete(fileId);
    }

    public int deleteAll(){
        return fileMapper.deleteAll();
    }

    public List<File> findAllFiles(){
        return fileMapper.getAll();
    }

    public File getItemById(Integer fileId){
        return fileMapper.getItemById(fileId);
    }
}
