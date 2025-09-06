package com.tech2tech.store;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {

    @RequestMapping("/")
    public String index(){
        return "login/index";
    }

    @GetMapping("/login")
    public String login(){
        return "login/login";
    }

}
