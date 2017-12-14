package com.uutic.website.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
public class MainController {
    @RequestMapping("/hello")
    public String hello() throws IOException, InterruptedException {
        Thread.sleep(5000);
        return new ObjectMapper().writeValueAsString("hello");
    }
}
