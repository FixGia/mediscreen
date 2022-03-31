package com.project.patienthistorical.repository;

import com.project.patienthistorical.model.PatientNote;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Repository
public interface PatientNoteRepository extends MongoRepository<PatientNote, String> {

    /**
     * Method to find Note By patientID
     *
     * @param patientId the patient ID
     *
     * @return a List which contain all notes for a patient
     */
    List<PatientNote> findByPatientId (final Integer patientId);

    /**
     * Method to find Note By date and doctorNote
     *
     * @param date the date
     * @param doctorNote the note
     *
     * @return a Note
     */
    PatientNote findPatientNoteByDateAndDoctorNote(LocalDate date, String doctorNote);

}
