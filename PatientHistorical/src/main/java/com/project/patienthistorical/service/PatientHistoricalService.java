package com.project.patienthistorical.service;

import com.project.patienthistorical.dto.PatientNoteRequest;
import com.project.patienthistorical.exception.DataAlreadyExistException;
import com.project.patienthistorical.exception.DataNotFoundException;

import java.util.List;


public interface PatientHistoricalService {

    PatientNoteRequest getPatientNote(String noteId) throws DataNotFoundException;

    PatientNoteRequest addPatientNote(PatientNoteRequest patientNoteRequest) throws DataAlreadyExistException;

    void deletePatientNote(String noteId) throws DataNotFoundException;

    PatientNoteRequest updatePatientNote(String noteId, PatientNoteRequest patientNoteRequest) throws DataNotFoundException;

    List<PatientNoteRequest> getAllNoteBelongPatient(Integer patientId) throws DataNotFoundException;
}
