package com.project.patient.service;


import com.project.patient.dto.PatientRequest;

import java.util.List;

public interface PatientService {


    List<PatientRequest> getAllPatients(String keyword);

    PatientRequest getPatientById(int patientId);

    PatientRequest updatePatient(int patientId, PatientRequest patientRequest);

    PatientRequest addPatient(PatientRequest patientRequest);

    void deletePatient(int patientId);

}
