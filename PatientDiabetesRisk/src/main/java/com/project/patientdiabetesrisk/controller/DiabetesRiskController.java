package com.project.patientdiabetesrisk.controller;

import com.project.patientdiabetesrisk.dto.DiabetesAssessment;
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

    /**
     * Controller Get - getRiskLevel
     *
     * @param patientId the patient ID
     *
     * @return a String which contain patient name, patient age, number of trigger and risk level
     */
    @GetMapping("/riskLevel/{id}")
    public String getRiskLevel(@PathVariable("id") Integer patientId) {

        log.debug("Controller: getRiskLevel for PatientDiabetesRisk - called");

        log.info("Controller: getRiskLevel for PatientDiabetesRisk  - success");
        return diabetesRiskService.finalRiskLevelTextResult(patientId);

    }

    /**
     * Controller Get - getDiabetesAssessment
     *
     * @param patientId the patient ID
     *
     * @return a DiabetesAssessment object
     */
    @GetMapping("/riskLevel/diabetesAssessment/{id}")
    public DiabetesAssessment getDiabetesAssessment(@PathVariable("id") Integer patientId){

        log.debug("Controller: getDiabetesAssessment for PatientDiabetesRisk - called");

        log.info("Controller: getDiabetesAssessment for PatientDiabetesRisk - success");
        return diabetesRiskService.getDiabetesAssessment(patientId);
    }
}
