package com.project.ui.dto;


import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class DiabetesAssessment {

    /**
     * a patient firstname
     */
    private String patientFirstName;

    /**
     * a patient lastname
     */
    private String patientLastName;

    /**
     * a patient age
     */
    private String patientAge;

    /**
     * a patient Risk Level
     */
    private String riskLevels;



}
