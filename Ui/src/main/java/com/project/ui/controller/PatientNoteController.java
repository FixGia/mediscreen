package com.project.ui.controller;

import com.project.ui.dto.PatientNoteRequest;
import com.project.ui.proxy.PatientHistoricalMicroService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.Banner;
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

    /**
     * Controller - GET - showNotePatientList
     * @param patientId the patient ID
     * @param model the model
     * @return a page which contains all notes belong to a patient
     */
    @GetMapping({"/list/{id}"})
    public String showNotePatientList(@PathVariable("id") final Integer patientId, Model model) {

        log.debug("UI - GET - /note/list/** - called");

        model.addAttribute("patientId", patientId);

        model.addAttribute("notes", microService.getPatientNoteList(patientId));

        log.info("UI - GET - /note/list/** - return patient Note List successfully");

        return "note/list";
    }

    /**
     * Controller - GET - deleteNote
     *
     * @param noteId the note ID
     * @param patientId the patient ID
     *
     * @return a page without the deleted note
     *
     */
    @GetMapping({"/delete/{id}/{patientId}"})
    public String deleteNote(@PathVariable("id") String noteId , @PathVariable("patientId") final Integer patientId) {

        log.debug("UI - GET - /note/delete/{}/{} - called", noteId, patientId);

        microService.deleteNote(noteId);

        log.info("UI - GET - /note/delete/{}/{} - note deleted successfully", noteId, patientId);

        return "redirect:/note/list/" + patientId;
    }

    /**
     * Controller -  GET - show update form
     *
     * @param noteId the note ID
     * @param model the model
     *
     * @return an update page
     *
     */
    @GetMapping({"/update/{id}"})
    public String showUpdateForm(@PathVariable("id") String noteId, final Model model) {

        log.debug("UI - GET - /note/update/** - called");
        PatientNoteRequest note = microService.getNoteById(noteId);

        model.addAttribute("note", note);

        log.info("UI - GET - /note/update/** - show update form successfully");

        return "note/update";
    }

    /**
     * Controller POST - updateNote
     *
     * @param noteId the note ID
     * @param patientNoteRequest the patientNoteRequest
     * @param result the BindingResult to handle errors
     *
     * @return a list of notes with the note updated
     *
     */
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

    /**
     * controller - GET - addNoteForm
     * @param model the model
     * @param patientId the patientID
     * @return an add page
     */
    @GetMapping({"/add/{id}"})
    public String addNoteForm(final Model model, @PathVariable("id")final Integer patientId){


        PatientNoteRequest note = new PatientNoteRequest();

        note.setPatientId(patientId);
        model.addAttribute("note", note);

        return "note/add";

    }

    /**
     * Controller - POST - validateAddNote
     *
     * @param patientNoteRequest the patientNoteRequest to add
     * @param result the BindingResult to handle errors
     * @param model the model
     *
     * @return a list of notes with a new note
     */
    @PostMapping({"/validate"})
    public String validateAddNote(@Valid final PatientNoteRequest patientNoteRequest, final BindingResult result, final Model model) {

        if (result.hasErrors()) {

            model.addAttribute("note", patientNoteRequest);

            return "note/add";

        } else {

           microService.addNote(patientNoteRequest);

            return "redirect:/note/list/" + patientNoteRequest.getPatientId();
        }
    }
}
