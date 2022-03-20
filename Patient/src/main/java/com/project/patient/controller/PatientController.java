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


    /**
     * Controller Get - get PatientList
     *
     * @param keyword the keyword for patient research
     *
     * @return List of patient
     */
    @GetMapping("/list")
    public List<PatientRequest> getPatientList(@RequestParam(value = "keyword", required = false) String keyword){

        log.debug("Controller: getPatientList - called");

        List<PatientRequest> patientRequestList= patientService.getAllPatients(keyword);

        log.info("Controller : getPatientLIst - success");

        return patientRequestList;
    }

    /**
     * Controller Get - get Patient
     *
     * @param patientId the patient Id
     *
     * @return a Patient
     */
    @GetMapping("/get/{id}")
    public PatientRequest getPatientById(@PathVariable("id") int patientId) {

        log.debug("Controller: getPatient - called");

        PatientRequest patientRequest = patientService.getPatientById(patientId);

        log.info("Controller: getPatient - success");

        return patientRequest;
    }

    /**
     * Controller - POST - update patient
     *
     * @param patientId the patient ID
     *
     * @param patientRequest the patient Request
     *
     * @return an updated patient
     */
    @PostMapping("/update/{id}")
    public PatientRequest updatePatient(@PathVariable("id") Integer patientId, @Valid @RequestBody PatientRequest patientRequest) {
        log.debug("Controller: updatePatient - called");

        PatientRequest patientToUpdate = patientService.updatePatient(patientId, patientRequest);

        log.info("Controller: updatePatient - success");

        return patientToUpdate;
    }

    /**
     * Controller - POST - add Patient
     *
     * @param patientRequest the patient to add
     *
     * @return a new patient
     */
    @PostMapping("/add")
    @ResponseStatus(HttpStatus.CREATED)
    public PatientRequest addPatient(@Valid @RequestBody final PatientRequest patientRequest) {

        log.debug("Controller: addPatient - called");

        PatientRequest patientToSave = patientService.addPatient(patientRequest);

        log.info("Controller: addPatient - success");

        return patientToSave;
    }

    /**
     * Controller -GET - delete patient
     *
     * @param patientId the if of patient to delete
     *
     */
    @GetMapping("/delete/{id}")
    public void deletePatient(@PathVariable("id") final int patientId){

        log.debug("Controller: deletePatient - called");

        patientService.deletePatient(patientId);

        log.info("Controller : deletePatient - success");
    }
}
