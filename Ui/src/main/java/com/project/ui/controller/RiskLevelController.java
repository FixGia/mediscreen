package com.project.ui.controller;

import com.project.ui.proxy.DiabetesRiskLevelMicroService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping({"/riskLevel"})
public class RiskLevelController {

    private final DiabetesRiskLevelMicroService microService;

    public RiskLevelController(DiabetesRiskLevelMicroService microService) {
        this.microService = microService;
    }


    /**
     * Controller - GET - show Risk Level
     *
     * @param patientId the patient ID
     * @param model the model
     *
     * @return a page with patient firstname, lastname, age and his diabetes risk level
     */
    @GetMapping({"/{id}"})
    public String showRiskLevel(@PathVariable("id") final Integer patientId, Model model){

        model.addAttribute("patientId", patientId);

        model.addAttribute("riskLevel", microService.getDiabetesAssessment(patientId));

        return "assessment/riskLevel";

    }
}
