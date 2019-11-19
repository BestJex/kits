package com.ptyt.haiguan.kits.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

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
}
