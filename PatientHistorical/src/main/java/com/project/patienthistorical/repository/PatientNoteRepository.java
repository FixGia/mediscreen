package com.project.patienthistorical.repository;

import com.project.patienthistorical.model.PatientNote;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Repository
public interface PatientNoteRepository extends MongoRepository<PatientNote, String> {

    List<PatientNote> findByPatientId (final Integer patientId);

    PatientNote findPatientNoteByDateAndDoctorNote(LocalDate date, String doctorNote);

}
