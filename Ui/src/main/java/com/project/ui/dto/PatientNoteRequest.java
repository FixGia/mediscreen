package com.project.ui.dto;

import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class PatientNoteRequest {

    /**
     * a patientNote id
     */
    private String id;

    /**
     * a patient ID
     */
    private Integer patientId;

    /**
     * a date
     */
    private LocalDate date;

    /**
     * a note write by a doctor
     */
    private String note;
}
