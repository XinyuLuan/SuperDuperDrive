package com.udacity.jwdnd.course1.cloudstorage.controller;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;

@Controller
public class HandleErrorController implements ErrorController {

    @RequestMapping("/error")
    public String handleEroor(HttpServletRequest request, Model model){
        //get the error code from request -- HttpServeletRequest
        Object status = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);

        String pageTitle = null;
        String errorMsg = null;

        if(status != null){
            Integer statusCode = Integer.valueOf(status.toString());
            if(statusCode == HttpStatus.NOT_FOUND.value()){
                pageTitle = "Page Not Found";
                errorMsg = "404 Error -- Page can't be found";
            }else if(statusCode == HttpStatus.INTERNAL_SERVER_ERROR.value()){
                //500
                pageTitle = "Internal server error";
                errorMsg = "500 Error -- Internal server error";
            }else if(statusCode == HttpStatus.FORBIDDEN.value()){
                //406
                pageTitle = "Forbidden error";
                errorMsg = "406 Error -- Page is Forbidden";
            }else{
                //everything else
                pageTitle = "Erorr of " + String.valueOf(statusCode);
                errorMsg = String.valueOf(statusCode) + " Error";
            }
        }

        model.addAttribute("pageTitle", pageTitle);
        model.addAttribute("errorMsg", errorMsg);

        return "error";
    }


    @Override
    public String getErrorPath() {
        return "error";
    }
}
