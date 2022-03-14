package com.project.patient.controller;



import com.project.patient.dto.PatientRequest;
import com.project.patient.service.PatientService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;


@RequestMapping("/patient")
@RestController
@Slf4j
public class PatientController {

    private final PatientService patientService;

    public PatientController(PatientService patientService) {
        this.patientService = patientService;
    }


    @GetMapping("/list")
    public List<PatientRequest> getPatientList(@RequestParam(value = "keyword", required = false) String keyword){

        log.debug("Controller: getPatientList - called");

        List<PatientRequest> patientRequestList= patientService.getAllPatients(keyword);

        log.info("Controller : getPatientLIst - success");

        return patientRequestList;
    }

    @GetMapping("/get/{id}")
    public PatientRequest getPatientById(@PathVariable("id") int patientId) {

        log.debug("Controller: getPatient - called");

        PatientRequest patientRequest = patientService.getPatientById(patientId);

        log.info("Controller: getPatient - success");

        return patientRequest;
    }

    @PostMapping("/update/{id}")
    public PatientRequest updatePatient(@PathVariable("id") Integer patientId, @Valid @RequestBody PatientRequest patientRequest) {
        log.debug("Controller: updatePatient - called");

        PatientRequest patientToUpdate = patientService.updatePatient(patientId, patientRequest);

        log.info("Controller: updatePatient - success");

        return patientToUpdate;
    }

    @PostMapping("/add")
    @ResponseStatus(HttpStatus.CREATED)
    public PatientRequest addPatient(@Valid @RequestBody final PatientRequest patientRequest) {

        log.debug("Controller: addPatient - called");

        PatientRequest patientToSave = patientService.addPatient(patientRequest);

        log.info("Controller: addPatient - success");

        return patientToSave;
    }

    @GetMapping("/delete/{id}")
    public void deletePatient(@PathVariable("id") final int patientId){

        log.debug("Controller: deletePatient - called");

        patientService.deletePatient(patientId);

        log.info("Controller : deletePatient - success");
    }
}
