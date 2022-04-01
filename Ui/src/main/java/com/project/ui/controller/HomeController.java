package com.project.ui.controller;

import com.project.ui.proxy.PatientMicroService;
import feign.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {


    private final PatientMicroService patientMicroService;

    public HomeController(PatientMicroService patientMicroService) {
        this.patientMicroService = patientMicroService;
    }

    /**
     * Method Controller - GET - homePage
     * @return the home page
     */
    @GetMapping({"/"})
    public String getHomePage(final Model model, @Param("keyword") final String keyword) {


        model.addAttribute("patients", patientMicroService.getPatientList(keyword));
        model.addAttribute("keyword", keyword);
        return "home";
    }


}