package com.project.ui.controller;


import com.project.ui.dto.PatientRequest;
import com.project.ui.proxy.PatientMicroService;
import feign.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
@RequestMapping({"/patient"})
public class PatientController {

    private final PatientMicroService patientMicroService;



    public PatientController(PatientMicroService patientMicroService) {
        this.patientMicroService = patientMicroService;
    }

    /**
     * Controller GET - PatientList
     * @param model the model
     * @param keyword the keyword for research
     * @return a PatientList
     */
    @GetMapping({"/list"})
    public String getPatientList(
            final Model model,
            @Param("keyword") final String keyword) {

        model.addAttribute("patients", patientMicroService.getPatientList(keyword));

        model.addAttribute("keyword", keyword);

        return "patient/list";
    }

    /**
     * Controller GET -  Update Form
     * @param patientId the patient ID
     * @param model the Model
     * @return the update Form
     */
    @GetMapping({"/update/{id}"})
    public String showUpdateForm(
            @PathVariable("id") final Integer patientId,
            final Model model) {

        PatientRequest patient = patientMicroService.getPatientById(patientId);

        model.addAttribute("patientRequest", patient);

        return "patient/update";
    }

    /**
     * Controller - POST - updatePatient
     * @param patientId the patient ID
     * @param patientRequest the patient's body for update
     * @param result a Binding result
     * @return the updated patient
     */
    @PostMapping({"/update/{id}"})
    public String updatePatient(
            @PathVariable("id") final Integer patientId,
            @Valid final PatientRequest patientRequest,
            final BindingResult result) {


        if (result.hasErrors()) {

            return "patient/update";
        } else {
            patientMicroService.updatePatient(patientId, patientRequest);

            return "redirect:/patient/list";
        }
    }


    /**
     * Controller - Get - add Form
     * @param model the model
     * @return the add Form
     */
    @GetMapping({"/add"})
    public String addPatientForm(final Model model) {

        model.addAttribute("patientRequest", new PatientRequest());

        return "patient/add";
    }


    /**
     * Controller - POST - add patient
     * @param patientRequest the patient to add
     * @param result a binding result
     * @return a new patient
     */
    @PostMapping({"/validate"})
    public String validate(
            @Valid final PatientRequest patientRequest,
            final BindingResult result) {

        if (result.hasErrors()) {
            return "patient/add";
        } else {
           patientMicroService.addPatient(patientRequest);
            return "redirect:/patient/list";
        }
    }


    /**
     * Controller - Get - delete Patient
     * @param patientId the patient ID
     * @return a list without the patient to delete
     */
    @GetMapping({"/delete/{id}"})
    public String deletePatient(@PathVariable("id") final Integer patientId) {

        patientMicroService.deletePatient(patientId);

        return "redirect:/patient/list";
    }

}
