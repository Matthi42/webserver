package com.schmalz.webserver.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class fragmentController {

    @GetMapping
    public String header(){
        return "/fragments/head";
    }
}
