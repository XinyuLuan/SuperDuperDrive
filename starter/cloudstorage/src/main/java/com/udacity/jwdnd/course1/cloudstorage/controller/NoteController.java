package com.udacity.jwdnd.course1.cloudstorage.controller;

import com.udacity.jwdnd.course1.cloudstorage.model.Note;
import com.udacity.jwdnd.course1.cloudstorage.model.view.NoteForm;
import com.udacity.jwdnd.course1.cloudstorage.services.NoteService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/note")
public class NoteController {

    private static final Logger log = LoggerFactory.getLogger(NoteController.class);

    @Autowired
    NoteService noteService;

    @PostMapping("/add")
    public String addNoteAction(NoteForm noteForm, Model model, HttpSession session){
        Integer userId = (int)session.getAttribute("userId");

        Note newNote = new Note(noteForm, userId);
        log.info(newNote.toString());
//        int result = (newNote.getNoteId() == null) ? noteService.insert(newNote) : noteService.update(newNote);
        int result;
        if(newNote.getNoteId() == null){

            log.info("inserting");
            result = noteService.insert(newNote);
        }
        else{
            log.info("updating");
            result = noteService.update(newNote);
        }

        if(result == 1){
            model.addAttribute("changeSuccess", true);

        }
        else{
            model.addAttribute("changeSuccess", false);
            model.addAttribute("changeErrorMsg", "Add on Notes Failed");
        }

        return "result";
    }

    @GetMapping("/delete")
    public String deleteNoteAction(@RequestParam(name="noteId") String noteId, Model model){
        int result = noteService.delete(Integer.parseInt(noteId));
        if(result == 1){
            model.addAttribute("changeSuccess", true);
        }
        else{
            model.addAttribute("changeSuccess", false);
            model.addAttribute("changeErrorMsg", "Delete unsuccessful");
        }
        return "result";
    }
}
