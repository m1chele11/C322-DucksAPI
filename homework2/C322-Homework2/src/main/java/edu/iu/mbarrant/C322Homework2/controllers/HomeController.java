package edu.iu.mbarrant.C322Homework2.controllers;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {


    @GetMapping("/")
    public String greeting() {
        return "Welcome to the Guitar service!";
    }

}
