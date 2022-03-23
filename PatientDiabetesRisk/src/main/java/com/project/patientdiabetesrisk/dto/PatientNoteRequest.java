package com.project.patientdiabetesrisk.dto;

import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class PatientNoteRequest {

    /**
     * The id
     */
    private String id;

    /**
     * The patient ID
     */
    private Integer patientId;

    /**
     * The note's date
     */
    private LocalDate date;

    /**
     * The note
     */
    private String note;


    /**
     * Instantiates a new note DTO.
     *
     * @param patientId the patient id
     * @param date the date
     * @param note the note
     */
    public PatientNoteRequest(
            final Integer patientId,
            final LocalDate date,
            final String note) {
        this.patientId = patientId;
        this.date = date;
        this.note = note;
    }
}
