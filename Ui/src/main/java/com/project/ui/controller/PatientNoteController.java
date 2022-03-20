package com.project.ui.controller;

import com.project.ui.dto.PatientNoteRequest;
import com.project.ui.proxy.PatientHistoricalMicroService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
@RequestMapping({"/note"})
@Slf4j
public class PatientNoteController {

    private final PatientHistoricalMicroService microService;

    public PatientNoteController(PatientHistoricalMicroService microService) {
        this.microService = microService;
    }


    @GetMapping({"/list/{id}"})
    public String showNotePatientList(@PathVariable("id") final Integer patientId, Model model) {

        log.debug("UI - GET - /note/list/** - called");

        model.addAttribute("patientId", patientId);

        model.addAttribute("notes", microService.getPatientNoteList(patientId));

        log.info("UI - GET - /note/list/** - return patient Note List successfully");

        return "note/list";
    }

    @GetMapping({"/delete/{id}/{patientId}"})
    public String deleteNote(@PathVariable("id")String noteId, @PathVariable("patientId") final Integer patientId) {

        log.debug("UI - GET - /note/delete/**/** - called");

        microService.deleteNote(noteId);

        log.info("UI - GET - /note/delete/**/** - note deleted successfully");

        return "redirect:/note/list/" + patientId;
    }

    @GetMapping({"/update/{id}"})
    public String showUpdateForm(@PathVariable("id") String noteId, final Model model) {

        log.debug("UI - GET - /note/update/** - called");
        PatientNoteRequest note = microService.getNoteById(noteId);

        model.addAttribute("note", note);

        log.info("UI - GET - /note/update/** - show update form successfully");

        return "note/update";
    }

    @PostMapping({"/update/{id}"})
    public String updateNote(@PathVariable("id") String noteId,
                             @Valid final PatientNoteRequest patientNoteRequest,
                             final BindingResult result) {

        if(result.hasErrors()) {

            return "note/update";
        }

        else {

            microService.updateNoteById(noteId,patientNoteRequest);

            return "redirect:/note/list/" + patientNoteRequest.getPatientId() ;

        }
    }

    @GetMapping({"/add"})
    public String addNoteForm(final Model model){

        model.addAttribute("note", new PatientNoteRequest());

        return "note/add";

    }

    @PostMapping({"/validate"})
    public String validateAddNote(@Valid final PatientNoteRequest patientNoteRequest, final BindingResult result) {

        if (result.hasErrors()) {
            return "note/add";
        } else {

           microService.addNote(patientNoteRequest);

            return "redirect:/note/list/" + patientNoteRequest.getPatientId();
        }
    }
}
