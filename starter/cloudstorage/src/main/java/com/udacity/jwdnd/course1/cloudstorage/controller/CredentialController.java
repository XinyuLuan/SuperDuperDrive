package com.udacity.jwdnd.course1.cloudstorage.controller;

import com.udacity.jwdnd.course1.cloudstorage.model.Credential;
import com.udacity.jwdnd.course1.cloudstorage.model.view.CredentialForm;
import com.udacity.jwdnd.course1.cloudstorage.services.CredentialService;
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
import java.util.List;

@Controller
@RequestMapping("/credential")
public class CredentialController {

    private static final Logger log = LoggerFactory.getLogger(CredentialController.class);

    @Autowired
    CredentialService credentialService;

    @GetMapping("/delete")
    public String deleteCredentialAction(@RequestParam(name="credentialId") String credenatialId, Model model){

        int result = credentialService.delete(Integer.parseInt(credenatialId));
        if(result == 1){
            model.addAttribute("changeSuccess", true);
        }
        else{
            model.addAttribute("changeSuccess", false);
            model.addAttribute("changeErrorMsg", "Delete Credential Failed");
        }

        return "result";
    }

    @PostMapping("/add")
    public String addCredentialAction(CredentialForm credentialForm, Model model, HttpSession session){
        Integer userId = (int)session.getAttribute("userId");

        Credential credential = new Credential(credentialForm, userId);
        int result;
        if(credentialForm.getCredentialId() == null || credentialForm.getCredentialId().length() == 0){
            result = credentialService.insert(credential);
        }
        else{
            result = credentialService.update(credential);
        }
        if(result == 1){
            model.addAttribute("changeSuccess", true);
        }
        else{
            model.addAttribute("changeSuccess", false);
            model.addAttribute("changeErrorMsg", "Add Credential Failed");
        }
        return "result";
    }
}
