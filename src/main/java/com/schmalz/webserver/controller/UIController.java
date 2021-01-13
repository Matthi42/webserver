package com.schmalz.webserver.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.time.LocalDateTime;

@Controller
public class UIController {
    @GetMapping("/")
    public String index(Model model){
        model.addAttribute("time", LocalDateTime.now().toString());
        return "index";
    }
}
