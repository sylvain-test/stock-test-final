package com.hopniel.gestionstock.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HomeController {

    @GetMapping("/")
    @ResponseBody
    public String home() {
        return "Welcome to Gestion Stock Application. <br><a href='/h2-console'>Access H2 Console</a>";
    }
}
