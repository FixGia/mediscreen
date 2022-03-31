package com.project.patient.service;

import com.project.patient.dto.PatientRequest;
import com.project.patient.exception.DataAlreadyExistException;
import com.project.patient.exception.DataNotFoundException;
import com.project.patient.model.Patient;
import com.project.patient.repository.PatientRepository;
import com.project.patient.util.Mapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;


@Service
@Slf4j
public class PatientServiceImpl implements PatientService{

    private final PatientRepository patientRepository;
    private final Mapper mapper;

    public PatientServiceImpl(PatientRepository patientRepository, Mapper mapper) {
        this.patientRepository = patientRepository;
        this.mapper = mapper;
    }

    /**
     * Method to Get List of All Patients
     *
     * @param keyword keyword for patient research
     *
     * @return List of patient
     */
    @Override
    public List<PatientRequest> getAllPatients(String keyword) {

        log.debug(" Service : get All Patients - supply ");

        if(keyword != null) {
            List<PatientRequest> patientRequestList = patientRepository.findByKeyword(keyword.toLowerCase()).stream()
                    .map(mapper::mapToPatientRequest).collect(Collectors.toList());

            log.info("Service : get All Patients with keyword - submit");
            return patientRequestList;
        }
        List<PatientRequest> strictlyAllPatient = patientRepository.findAll().stream()
                .map(mapper::mapToPatientRequest)
                .collect(Collectors.toList());

        log.info("Service : get strictly all patients - submit");
        return strictlyAllPatient;
    }

    /**
     * Method to Get Patient by ID
     *
     * @param patientId the patient id
     *
     * @return the patient
     *
     */
    @Override
    public PatientRequest getPatientById(int patientId) {

        log.debug("Service : get Patient - supply");
        Patient patient = patientRepository.findById(patientId)
                .orElseThrow(() -> new DataNotFoundException("Patient doesn't exist"));

        log.info("Service : get Patient - submit");
        return mapper.mapToPatientRequest(patient);

    }

    /**
     * Method to update Patient
     *
     * @param patientId the patient id
     *
     * @param patientRequest the patient request
     *
     * @return The patient updated
     *
     */
    @Override
    public PatientRequest updatePatient(int patientId, PatientRequest patientRequest) {


        log.debug("Service : update Patient - supply");
        Patient patientToUpdate = patientRepository.findById(patientId).orElseThrow(() -> new DataNotFoundException("Patient doesn't exist"));

        patientToUpdate.setId(patientRequest.getId());
        patientToUpdate.setFirstName(patientRequest.getFirstName());
        patientToUpdate.setLastName(patientRequest.getLastName());
        patientToUpdate.setBirthDate(patientRequest.getBirthDate());
        patientToUpdate.setSex(patientRequest.getSex());
        patientToUpdate.setAddress(patientRequest.getAddress());
        patientToUpdate.setPhoneNumber(patientRequest.getPhoneNumber());


        log.info("Service : update Patient - check");
        patientRepository.save(patientToUpdate);
        return mapper.mapToPatientRequest(patientToUpdate);


    }

    /**
     * Method to delete patient
     *
     * @param patientId the patient Id
     *
     */
    @Override
    public void deletePatient(int patientId) {

        log.debug("Service : delete Patient - supply");

        patientRepository.findById(patientId).orElseThrow(() -> new DataNotFoundException("Patient doesn't exist"));

        patientRepository.deleteById(patientId);

        log.info("Service : delete Patient - check");
    }

    /**
     * Method to add Patient
     *
     * @param patientRequest the patient request
     *
     * @return a new Patient
     *
     */
    public PatientRequest addPatient(PatientRequest patientRequest) {

        log.debug("Service : add Patient - supply");

        Patient patientToAdd = patientRepository.findByLastNameAndFirstNameAndBirthDate(patientRequest.getLastName(),
                patientRequest.getFirstName(),
                patientRequest.getBirthDate());

        if (patientToAdd != null) {
            throw new DataAlreadyExistException("The patient already exist");
        }

      Patient patientToSave =   patientRepository.save(mapper.mapToPatient(patientRequest));

        log.info("Service : addPatient - submit");

        return mapper.mapToPatientRequest(patientToSave);
    }
}
