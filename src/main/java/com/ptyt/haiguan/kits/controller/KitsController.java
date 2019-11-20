package com.ptyt.haiguan.kits.controller;

import com.ptyt.haiguan.kits.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletResponse;

/**
 * @author: yq
 * @date: 2019/11/15 13:55
 * @description:
 */
@Controller
@RequestMapping("/kits")
public class KitsController {

//    @Value("${spring.loginTitle}")
    private String loginTitle = "";

    @Autowired
    private FileService fileService;

    @Value("${spring.fileLocation}")
    private String fileLocation;

    @GetMapping("/login")
    public String login(Model model) {
        model.addAttribute("loginTitle",loginTitle);
        return "login";
    }

    @DeleteMapping("/logout")
    public void logout() {

    }

    @GetMapping("/index")
    public String index() {
        return "index";
    }

    @GetMapping("/success")
    public String success() {
        return "success";
    }

    @GetMapping("/download")
    public void download(@RequestParam String filePath , HttpServletResponse response) {
        fileService.download(fileLocation + "/" + filePath , response);
    }
}
