package com.project.ui.proxy;

import com.project.ui.dto.PatientRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import java.util.List;


@FeignClient(value ="patient-microService",  url = "${PROXY_PATIENT:http://localhost:9011/patient}")
public interface PatientMicroService {


        /**
         * Method to get a List of Patient
         *
         * @param keyword the keyword for research
         *
         * @return a List of patient
         */
        @GetMapping({"/list"})
        List<PatientRequest> getPatientList(@RequestParam(value = "keyword", required = false) String keyword);


        /**
         * Method to update a Patient
         *
         * @param patientId the patient ID
         *
         * @param patientRequest the patientRequest for update
         *
         * @return the updated patient
         */
        @PostMapping({"/update/{id}"})
        PatientRequest updatePatient(@PathVariable("id") final Integer patientId, PatientRequest patientRequest);


        /**
         * Method to get Patient
         *
         * @param patientId the patient ID
         *
         * @return a patient
         */
        @GetMapping({"/get/{id}"})
        PatientRequest getPatientById(@PathVariable("id") final Integer patientId);


        /**
         * Method to add a patient
         *
         * @param patientRequest the patient to add
         *
         * @return a new patient
         */
        @PostMapping({"/add"})
        PatientRequest addPatient(PatientRequest patientRequest);

        /**
         * Method to delete Patient
         *
         * @param patientId the patient Id
         *
         */
        @GetMapping({"/delete/{id}"})
        void deletePatient(@PathVariable("id") Integer patientId);

    }

