package com.tech2tech.store.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tech2tech.store.entities.Message;

@RestController
public class MessageController {

    @GetMapping("/hello")
    public Message sayHello(){
        return new Message("Hello world!");
    }

}
