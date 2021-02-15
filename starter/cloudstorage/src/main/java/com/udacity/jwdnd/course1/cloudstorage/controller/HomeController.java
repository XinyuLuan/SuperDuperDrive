package com.udacity.jwdnd.course1.cloudstorage.controller;

import com.udacity.jwdnd.course1.cloudstorage.model.Credential;
import com.udacity.jwdnd.course1.cloudstorage.model.Note;
import com.udacity.jwdnd.course1.cloudstorage.model.view.NoteForm;
import com.udacity.jwdnd.course1.cloudstorage.services.CredentialService;
import com.udacity.jwdnd.course1.cloudstorage.services.EncryptionService;
import com.udacity.jwdnd.course1.cloudstorage.services.NoteService;
import com.udacity.jwdnd.course1.cloudstorage.services.UserService;
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
            // how does this work ?????????????
        }
        else{
            userId = Integer.valueOf((int)session.getAttribute("userId"));;
        }

        model.addAttribute("enS", encryptionService);
        // how does this encryptionService work ??????

        List<Note> notesList = noteService.getNote(userId);
        for(int i = 0; i< 3; i++) {
            notesList.add(TestConstant.getNote(i, userId));
        }
        model.addAttribute("notesList", notesList);

        NoteForm noteForm = new NoteForm();
        model.addAttribute("noteForm", noteForm);

        List<Credential> credentialsList = credentialService.getCredential(userId);
        for(int i = 0; i < 3; i++){
            credentialsList.add(TestConstant.getCredential(userId));
        }

        model.addAttribute("credentialList", credentialsList);


        return "home";
    }

}
