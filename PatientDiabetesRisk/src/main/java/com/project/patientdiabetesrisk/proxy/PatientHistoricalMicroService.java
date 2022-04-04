package com.project.patientdiabetesrisk.proxy;


import com.project.patientdiabetesrisk.dto.PatientNoteRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;


@FeignClient(value ="patient-historical-microService",  url = "${PROXY_PATIENT_HISTORICAL:http://patient-historical:9012/note}")
public interface PatientHistoricalMicroService {

    /**
     * Method to get PatientNoteList from PatientHistorical MicroService
     *
     * @param patientId the patient id
     *
     * @return List of note
     */
    @GetMapping({"/list/{patientId}"})
    List<PatientNoteRequest> getPatientNoteList(@PathVariable Integer patientId);


}
