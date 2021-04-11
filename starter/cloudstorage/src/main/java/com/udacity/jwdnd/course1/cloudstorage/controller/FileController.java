package com.udacity.jwdnd.course1.cloudstorage.controller;


import com.udacity.jwdnd.course1.cloudstorage.model.File;
import com.udacity.jwdnd.course1.cloudstorage.model.view.FileForm;
import com.udacity.jwdnd.course1.cloudstorage.services.FileService;
import com.udacity.jwdnd.course1.cloudstorage.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/file")
public class FileController implements HandlerExceptionResolver {

    private static Logger logger = LoggerFactory.getLogger(FileController.class);

    @Autowired
    FileService fileService;

    @PostMapping("/add")
    public String uploadOneFile(Model model, HttpSession session, FileForm fileForm){

        int userId = (int) session.getAttribute("userId");
        if(fileForm == null ){
            String errorMsg = "No File is Selected";
            model.addAttribute("changeSuccess", false);
            model.addAttribute("changeErrorMsg", errorMsg);
            return "result";
        }
        if(fileForm.getFileId() == null || fileForm.getFileId().isEmpty()){
            addingFile(fileForm, userId, model);
        }
        else{
           updatingFile(fileForm, userId, model);
        }

        model.addAttribute("fileForm", new FileForm());
        return "result";

    }

    @GetMapping("/view")
    public ResponseEntity<Object> viewOneFile(@RequestParam(name = "fileId") String fileId, Model model){
        File file = fileService.getItemById(Integer.parseInt(fileId));

        HttpHeaders headers = new HttpHeaders();
        headers.add( HttpHeaders.CONTENT_DISPOSITION, String.format("attachment; filename=\"%s\"", file.getFileName()));

        ResponseEntity<Object> responseEntity =
                ResponseEntity.ok()
                        .headers(headers)
                        .contentLength(file.getFileData().length)
                        .body(file.getFileData());

        return responseEntity;
    }

    @GetMapping("/delete")
    public String deleteOneFile(@RequestParam(name = "fileId") String fileId, Model model){
        int result = fileService.delete(Integer.parseInt(fileId));

        if(result != 1){
            model.addAttribute("changeSuccess", false);
            model.addAttribute("changeErrorMsg", "Delete File FAILED");
        }

        model.addAttribute("changeSuccess", true);
        return "result";
    }

    @Override
    public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {

        ModelAndView modelAndView = new ModelAndView("result");
        if (ex instanceof MaxUploadSizeExceededException) {
            modelAndView.getModel().put("errorResultMessage", "File size exceeds LIMITED");
        }
        return modelAndView;
    }

    private void updatingFile(FileForm fileForm, int userId, Model model){
        int updated;
        try{
            updated = fileService.updateFile(fileForm.getUpdatedFile(), userId);
        }
        catch (Exception e){
            updated = -1;
        }

        if(updated != 1){
            model.addAttribute("changeSuccess", false);
            String errorMsg = "Updated file FAILED";
            model.addAttribute("changeErrorMsg", errorMsg);
        }
        else{
            model.addAttribute("changeSuccess", true);
        }
    }

    private void addingFile(FileForm fileForm, int userId, Model model){
        // new file
        if(!fileService.hasDuplicationFiles(userId, fileForm.getUpdatedFile().getOriginalFilename())){
            if(fileForm.getUpdatedFile().getOriginalFilename() == null || fileForm.getUpdatedFile().getOriginalFilename().isEmpty()){
                String errorMsg = "File is NOT FOUND";
                model.addAttribute("changeErrorMsg", errorMsg);
                model.addAttribute("changeSuccess", false);
                return;
            }

            int uploaded;
            try{
                uploaded = fileService.insertFile(fileForm.getUpdatedFile(), userId);
            }
            catch (Exception exception){
                uploaded = -1;
            }

            if(uploaded != 1){
                model.addAttribute("changeSuccess", false);
                String errorMsg = "Upload file failed";
                model.addAttribute("changeErrorMsg", errorMsg);
            }
            else{
                model.addAttribute("changeSuccess", true);
            }
        }
        // duplicate file
        else{
            model.addAttribute("changeSuccess", false);
            String errorMsg = "The file is already EXIST";
            model.addAttribute("changeErrorMsg", errorMsg);
        }
    }
}
