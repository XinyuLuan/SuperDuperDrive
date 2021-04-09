package com.udacity.jwdnd.course1.cloudstorage.controller;

import com.udacity.jwdnd.course1.cloudstorage.model.Credential;
import com.udacity.jwdnd.course1.cloudstorage.model.File;
import com.udacity.jwdnd.course1.cloudstorage.model.Note;
import com.udacity.jwdnd.course1.cloudstorage.model.view.CredentialForm;
import com.udacity.jwdnd.course1.cloudstorage.model.view.FileForm;
import com.udacity.jwdnd.course1.cloudstorage.model.view.NoteForm;
import com.udacity.jwdnd.course1.cloudstorage.services.*;
import com.udacity.jwdnd.course1.cloudstorage.utils.TestConstant;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
//@RequestMapping("/home")
public class HomeController {

    private static final Logger log = LoggerFactory.getLogger(HomeController.class);

    @Autowired
    private UserService userService;

    @Autowired
    private EncryptionService encryptionService;

    @Autowired
    private NoteService noteService;

    @Autowired
    private CredentialService credentialService;

    @Autowired
    private FileService fileService;

    public HomeController(UserService userService, EncryptionService encryptionService) {
        this.userService = userService;
        this.encryptionService = encryptionService;
    }

    @GetMapping(value = {"/", "/home"})
    public String toHomeView(Authentication authentication, Model model, HttpSession session){

        Integer userId = null;
        if(session.getAttribute("userId") == null){
            String username = authentication.getName();
            userId = Integer.valueOf(userService.getUser(username).getUserid());
            session.setAttribute("userId", userId);
        }
        else{
            userId = Integer.valueOf((int)session.getAttribute("userId"));
        }

        model.addAttribute("enS", encryptionService);

        List<File> filesList = fileService.findAllFiles();
        for(File file : filesList){
            log.info("HomeController/home: " + file.getFileName());
        }
        model.addAttribute("filesList", filesList);

        FileForm fileForm = new FileForm();
        model.addAttribute("fileForm", fileForm);

        List<Note> notesList = noteService.getNote(userId);
        model.addAttribute("notesList", notesList);

        NoteForm noteForm = new NoteForm();
        model.addAttribute("noteForm", noteForm);

        List<Credential> credentialsList = credentialService.getCredential(userId);
        model.addAttribute("credentialList", credentialsList);

        CredentialForm credentialForm = new CredentialForm();
        model.addAttribute("credentialForm", credentialForm);

        return "home";
    }

}
