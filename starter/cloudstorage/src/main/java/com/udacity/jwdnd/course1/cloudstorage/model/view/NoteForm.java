package com.udacity.jwdnd.course1.cloudstorage.model.view;

public class NoteForm {
    private String noteTitle;
    private String noteDescription;
    private String noteId;

    public NoteForm(String noteId, String noteTitle, String noteDescription) {
        this.noteId = noteId;
        this.noteTitle = noteTitle;
        this.noteDescription = noteDescription;
    }

    public NoteForm(){
        this.noteId = null;
        this.noteTitle = null;
        this.noteDescription = null;
    }

    public String getNoteTitle() {
        return noteTitle;
    }

    public void setNoteTitle(String noteTitle) {
        this.noteTitle = noteTitle;
    }

    public String getNoteDescription() {
        return noteDescription;
    }

    public void setNoteDescription(String noteDescription) {
        this.noteDescription = noteDescription;
    }

    public String getNoteId() {
        return noteId;
    }

    public void setNoteId(String noteId) {
        this.noteId = noteId;
    }
}
