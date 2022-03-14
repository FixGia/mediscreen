package com.project.patient.util;


import com.project.patient.dto.PatientRequest;
import com.project.patient.model.Patient;
import org.springframework.stereotype.Component;

@Component
public class Mapper {


    public Patient mapToPatient(final PatientRequest patientRequest) {

        return new Patient(
                patientRequest.getLastName(),
                patientRequest.getFirstName(),
                patientRequest.getBirthDate(),
                patientRequest.getSex(),
                patientRequest.getAddress(),
                patientRequest.getPhoneNumber());
    }


    public PatientRequest mapToPatientRequest(final Patient patient){

        return new PatientRequest(patient.getId(),
                patient.getLastName(),
                patient.getFirstName(),
                patient.getBirthDate(),
                patient.getSex(),
                patient.getAddress(),
                patient.getPhoneNumber());
    }

}
