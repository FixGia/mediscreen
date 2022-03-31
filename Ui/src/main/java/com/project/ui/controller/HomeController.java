package com.project.ui.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {


    /**
     * Method Controller - GET - homePage
     * @return the home page
     */
    @GetMapping({"/"})
    public String getHomePage() {

        return "home";
    }


}