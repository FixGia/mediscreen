package com.project.patientdiabetesrisk.proxy;

import com.project.patientdiabetesrisk.dto.PatientRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(value ="patient-microService",  url = "${PROXY_PATIENT:http://localhost:9011/patient}")
public interface PatientMicroService {




    /**
     * Method to get Patient
     *
     * @param patientId the patient ID
     *
     * @return a patient
     */
    @GetMapping({"/get/{id}"})
    PatientRequest getPatientById(@PathVariable("id") final Integer patientId);




}
