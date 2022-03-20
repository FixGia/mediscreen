package com.project.patienthistorical.dto;

import lombok.*;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class PatientNoteRequest {

    private String id;

    private Integer patientId;

    private LocalDate date;

    private String note;
}
