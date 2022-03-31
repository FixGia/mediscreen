package com.project.patient.service;


import com.project.patient.dto.PatientRequest;

import java.util.List;

public interface PatientService {

    /**
     * Method to get All patients
     *
     * @param keyword the keyword
     *
     * @return a List which contains PatientRequests
     */
    List<PatientRequest> getAllPatients(String keyword);

    /**
     * Method to get patient by id
     *
     * @param patientId the patient ID
     *
     * @return a PatientRequest
     */
    PatientRequest getPatientById(int patientId);

    /**
     * Method to updatePatient
     *
     * @param patientId the patient ID
     * @param patientRequest a patientRequest
     *
     * @return an update PatientRequest
     */
    PatientRequest updatePatient(int patientId, PatientRequest patientRequest);

    /**
     * Method to add a patient
     *
     * @param patientRequest a patientRequest
     *
     * @return a new patient
     */
    PatientRequest addPatient(PatientRequest patientRequest);

    /**
     * Method to delete Patient
     *
     * @param patientId the patient ID to delete
     */
    void deletePatient(int patientId);

}
