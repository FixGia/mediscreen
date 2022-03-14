package com.project.ui.proxy;

import com.project.ui.dto.PatientRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import java.util.List;


@FeignClient(value ="patient-microService",  url = "${PROXY_PATIENT:http://localhost:8081/patient}")
public interface PatientMicroService {


        @GetMapping({"/list"})
        List<PatientRequest> getPatientList(@RequestParam(value = "keyword", required = false) String keyword);


        @PostMapping({"/update/{id}"})
        PatientRequest updatePatient(@PathVariable("id") final Integer patientId, PatientRequest patientRequest);


        @GetMapping({"/get/{id}"})
        PatientRequest getPatientById(@PathVariable("id") final Integer patientId);


        @PostMapping({"/add"})
        PatientRequest addPatient(PatientRequest patientDTO);

        @GetMapping({"/delete/{id}"})
        void deletePatient(@PathVariable("id") Integer patientId);

    }

