package com.project.patientdiabetesrisk.controller;

import com.project.patientdiabetesrisk.service.DiabetesRiskService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class DiabetesRiskController {

    private final DiabetesRiskService diabetesRiskService;

    public DiabetesRiskController(DiabetesRiskService diabetesRiskService) {
        this.diabetesRiskService = diabetesRiskService;
    }

    @GetMapping("/riskLevel/{id}")
    public String getRiskLevel(@PathVariable("id") Integer patientId) {

       return diabetesRiskService.finalTextResult(patientId);

    }
}
