package com.project.patientdiabetesrisk.constant;

import lombok.Getter;

@Getter
public enum RiskLevels {

    /**
     * The none
     */
    NONE("None"),

    /**
     * The borderline
     */
    BORDERLINE("Borderline"),

    /**
     * The In danger

     */
    IN_DANGER("In danger"),

    /**
     * The early onset
     */
    EARLY_ONSET("Early onset");

    /**
     * the risk level
     */
    private String riskLevel;

    /**
     * Instantiate a new risk Level
     *
     * @param riskLevel the risk level
     *
     */
    RiskLevels(String riskLevel) {

        this.riskLevel = riskLevel;

    }


}
