package com.project.patientdiabetesrisk.dto;


import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class DiabetesAssessment {

    /**
     * the patient firstname
     */
    private String patientFirstName;

    /**
     * the patient lastname
     */
    private String patientLastName;

    /**
     * the patient age
     */
    private String patientAge;

    /**
     * the diabetes risk level
     */
    private String riskLevels;


}
