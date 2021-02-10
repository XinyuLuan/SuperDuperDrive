package com.udacity.jwdnd.course1.cloudstorage.services;

import com.udacity.jwdnd.course1.cloudstorage.mapper.NoteMapper;
import com.udacity.jwdnd.course1.cloudstorage.model.Note;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NoteService {

    @Autowired
    private final NoteMapper noteMapper;

    public NoteService(NoteMapper noteMapper) {
        this.noteMapper = noteMapper;
    }

    public int insert(Note note){
        return noteMapper.insert(note);
    }

    public List<Note> getNote(Integer userId){
        return noteMapper.getNote(userId);
    }

    public List<Note> getAllNotes(){
        return noteMapper.getAllNotes();
    }

    public int delete(Integer userId){
        return noteMapper.delete(userId);
    }

    public int deleteAll(){
        return noteMapper.deleteAll();
    }

    public int update(Note note){
        return noteMapper.update(note);
    }
}
