package com.project.patienthistorical.util;


import com.project.patienthistorical.dto.PatientNoteRequest;
import com.project.patienthistorical.model.PatientNote;
import org.springframework.stereotype.Component;

@Component
public class Mapper {

    /**
     * Method to map PatientNoteRequest to PatientNote
     * @param patientNoteRequest the patientNoteRequest to map
     * @return the patientNote mapped
     */
    public PatientNote mapToPatientNote(final PatientNoteRequest patientNoteRequest) {

        return new PatientNote(patientNoteRequest.getPatientId(),
                patientNoteRequest.getDate(),
                patientNoteRequest.getNote());
    }

    /**
     * Method to map PatientNote to PatientNoteRequest
     * @param patientNote the patientNote to map
     * @return the patientNoteRequest mapped
     */
    public PatientNoteRequest mapToPatientNoteRequest(final PatientNote patientNote) {

        return new PatientNoteRequest(patientNote.getId(),
                patientNote.getPatientId(),
                patientNote.getDate(),
                patientNote.getDoctorNote());
    }

}
