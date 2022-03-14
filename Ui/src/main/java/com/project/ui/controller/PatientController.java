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

    @GetMapping({"/list"})
    public String getPatientList(
            final Model model,
            @Param("keyword") final String keyword) {

        model.addAttribute("patients", patientMicroService.getPatientList(keyword));

        model.addAttribute("keyword", keyword);

        return "patient/list";
    }

    @GetMapping({"/update/{id}"})
    public String showUpdateForm(
            @PathVariable("id") final Integer patientId,
            final Model model) {

        PatientRequest patient = patientMicroService.getPatientById(patientId);

        model.addAttribute("patientRequest", patient);

        return "patient/update";
    }

    @PostMapping({"/update/{id}"})
    public String updateUser(
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




    @GetMapping({"/add"})
    public String addPatientForm(final Model model) {

        model.addAttribute("patientRequest", new PatientRequest());

        return "patient/add";
    }




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




    @GetMapping({"/delete/{id}"})
    public String deletePatient(@PathVariable("id") final Integer patientId) {

        patientMicroService.deletePatient(patientId);

        return "redirect:/patient/list";
    }

}
