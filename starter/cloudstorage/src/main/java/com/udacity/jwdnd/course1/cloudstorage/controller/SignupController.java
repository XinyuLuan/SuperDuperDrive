package com.udacity.jwdnd.course1.cloudstorage.controller;

import com.udacity.jwdnd.course1.cloudstorage.model.User;
import com.udacity.jwdnd.course1.cloudstorage.model.view.SignupForm;
import com.udacity.jwdnd.course1.cloudstorage.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/signup")
public class SignupController {

    @Autowired
    private UserService userService;

    @GetMapping
    public String toView(){
        return "signup";
    }

    @PostMapping
    // Model is used for data to talk to html page
    public String getPost(@ModelAttribute SignupForm signupForm, Model model, RedirectAttributes redirAttrs){
        String signUpErrorMsg = null;
        String signupSuccessMsg = null;
        if(!userService.isUsernameAvailable(signupForm.getUsername())){
            signUpErrorMsg = "The username is already exist.";
            model.addAttribute("signupError", signUpErrorMsg);
            return "signup";
        }
        if(signUpErrorMsg == null){
            User user = new User(signupForm);
            int addRow = userService.CreateUser(user);
            if(addRow == 0){
                signUpErrorMsg = "Error during the sign up process, please try again";
                model.addAttribute("signupError", signUpErrorMsg);
                return "signup";
            }
        }
        signupSuccessMsg = "Signup Successful";
        model.addAttribute("signupSuccess", true);
//        redirAttrs.addFlashAttribute("signupSuccess", true);
        return "login";
//        return "signup";  // do all function in controller need to return the string?
    }
}
