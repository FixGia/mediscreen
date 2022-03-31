package com.project.patienthistorical.model;


import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Document(collection = "PatientHistorical")
public class PatientNote {

    /**
     * The note ID
     */
    @Id
    private String id;

    /**
     * The patient ID
     */
    @Field(value = "patient_id")
    private Integer patientId;

    /**
     * The date
     */
    private LocalDate date;

    /**
     * The note
     */
    private String doctorNote;



    /**
     * Instantiates a new note.
     *
     * @param patientId the patient id
     * @param date the date
     * @param doctorNote the note
     */
    public PatientNote(
            final Integer patientId,
            final LocalDate date,
            final String doctorNote) {
        this.patientId = patientId;
        this.date = date;
        this.doctorNote = doctorNote;
    }


}


