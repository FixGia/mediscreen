package com.project.patientdiabetesrisk.service;

import com.project.patientdiabetesrisk.dto.DiabetesAssessment;

public interface DiabetesRiskService {


    /**
     * Method to get Diabetes risk level as a String
     *
     * @param patientId the patient ID
     *
     * @return a String which contain patient name,
     * patient age,
     * the trigger's number
     * and the diabetes risk level
     */
    String finalTextResult(Integer patientId);

    /**
     * Method to get Diabetes risk level as an object
     *
     * @param patientId the patient ID
     *
     * @return a DiabetesAssessment object
     */
    DiabetesAssessment getDiabetesAssessment(Integer patientId);
}
