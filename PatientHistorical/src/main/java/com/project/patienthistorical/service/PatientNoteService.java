package com.project.patienthistorical.service;

import com.project.patienthistorical.dto.PatientNoteRequest;
import com.project.patienthistorical.exception.DataAlreadyExistException;
import com.project.patienthistorical.exception.DataNotFoundException;

import java.util.List;


public interface PatientNoteService {


    /**
     * Method to get a PatientNote with id
     *
     * @param noteId the note ID
     *
     * @return a PatientNoteRequest
     *
     * @throws DataNotFoundException The DataNotFoundException mean data doesn't found in DB
     */
    PatientNoteRequest getPatientNote(String noteId) throws DataNotFoundException;

    /**
     * Method to add a PatientNote
     *
     * @param patientNoteRequest the Note to add
     *
     * @return a new Note
     *
     * @throws DataAlreadyExistException The DataAlreadyExistException mean data already exist in DB
     */
    PatientNoteRequest addPatientNote(PatientNoteRequest patientNoteRequest) throws DataAlreadyExistException;

    /**
     * Method to delete PatientNote
     *
     * @param noteId the Note ID
     *
     * @throws DataNotFoundException The DataNotFoundException mean data doesn't found in DB
     */
    void deletePatientNote(String noteId) throws DataNotFoundException;

    /**
     * Method to update PatientNote
     *
     * @param noteId the note ID
     * @param patientNoteRequest the patientNoteRequest
     *
     * @return a update PatientNote
     *
     * @throws DataNotFoundException The DataNotFoundException mean data doesn't found in DB
     */
    PatientNoteRequest updatePatientNote(String noteId, PatientNoteRequest patientNoteRequest) throws DataNotFoundException;

    /**
     * Method to get All notes belong to a patient
     *
     * @param patientId the patient ID
     *
     * @return a List of All notes belong to this patient
     *
     * @throws DataNotFoundException The DataNotFoundException mean data doesn't found in DB
     */
    List<PatientNoteRequest> getAllNoteBelongPatient(Integer patientId) throws DataNotFoundException;
}
