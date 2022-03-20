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

    @Id
    private String id;

    @Field(value = "patient_id")
    private Integer patientId;

    private LocalDate date;

    private String DoctorNote;







}


