package com.project.ui.proxy;


import com.project.ui.dto.DiabetesAssessment;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(value ="patient-diabetesRiskLevel-microService",  url = "${PROXY_PATIENT_DIABETES_RISK_LEVEL:http://localhost:9013/riskLevel}")
public interface DiabetesRiskLevelMicroService {

    /**
     * Method to getRiskLevel from Module PatientDiabetesRisk
     *
     * @param patientId the patient ID
     *
     * @return a String which contains patient firstname, lastname, age and his diabetes risk level
     */
    @GetMapping("/{id}")
    String getRiskLevel(@PathVariable("id") Integer patientId);

    /**
     * Method to getRisklevel from Module PatientDiabetesRisk
     *
     * @param patientId the patient ID
     *
     * @return an object call DiabetesAssessment which contain patient firstname, lastname, age and his diabetes risk level
     */
    @GetMapping("/diabetesAssessment/{id}")
    DiabetesAssessment getDiabetesAssessment(@PathVariable("id") Integer patientId);
}
