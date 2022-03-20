package com.project.patienthistorical.controller;

import com.project.patienthistorical.dto.PatientNoteRequest;
import com.project.patienthistorical.exception.DataAlreadyExistException;
import com.project.patienthistorical.exception.DataNotFoundException;
import com.project.patienthistorical.service.PatientHistoricalService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;


@RequestMapping("/patientHistorical")
@RestController
@Slf4j
public class PatientHistoricalController {

    private final PatientHistoricalService patientHistoricalService;

    public PatientHistoricalController(PatientHistoricalService patientHistoricalService) {
        this.patientHistoricalService = patientHistoricalService;
    }

    /**
     * Controller GET - PatientNoteList by patient ID
     *
     * @param patientId the patientId
     *
     * @return List of Note for one Patient
     *
     * @throws DataNotFoundException patientID doesn't found
     *
     */
    @GetMapping("/list/{patientId}")
    public List<PatientNoteRequest> getPatientNoteList(@PathVariable Integer patientId) throws DataNotFoundException {

        log.debug("Controller: getListNote for patient - called");

        List<PatientNoteRequest> notes = patientHistoricalService.getAllNoteBelongPatient(patientId);

        log.info("Controller:  getListNote for patient - success");

        return notes;
    }

    /**
     * Controller GET - Note by ID
     *
     * @param noteId the note id
     *
     * @return a note
     *
     * @throws DataNotFoundException note id doesn't found
     *
     */
    @GetMapping("/get/{id}")
    public PatientNoteRequest getNoteById(@PathVariable("id") String noteId ) throws DataNotFoundException {

        log.debug("Controller: getNote - called");

        PatientNoteRequest note = patientHistoricalService.getPatientNote(noteId);

        log.info("Controller : getNote - success");

        return note;

    }

    /**
     * Controller POST - update Note by Note ID
     *
     * @param noteId the note id
     *
     * @param patientNoteRequest the patientNoteRequest
     *
     * @return an update Note
     *
     * @throws DataNotFoundException Note id doesn't found
     *
     */
    @PostMapping("/update/{id}")
    public PatientNoteRequest updateNoteById(@PathVariable("id") String noteId, @Valid @RequestBody PatientNoteRequest patientNoteRequest) throws DataNotFoundException {
        log.debug("Controller: updateNoteById - called");

        PatientNoteRequest patientNoteToUpdate = patientHistoricalService.updatePatientNote(noteId,patientNoteRequest);

        log.info("Controller: updateNoteById - success");

        return patientNoteToUpdate;
    }

    /**
     *
     * Controller POST - add Note
     *
     * @param patientNoteRequest the patientNoteRequest
     *
     * @return a new Note
     *
     * @throws DataAlreadyExistException the Note already exist
     *
     */
    @PostMapping("/add")
    @ResponseStatus(HttpStatus.CREATED)
    public PatientNoteRequest addPatientNote(@Valid @RequestBody final PatientNoteRequest patientNoteRequest) throws DataAlreadyExistException {

        log.debug("Controller : addNote - called");

        PatientNoteRequest noteToSave = patientHistoricalService.addPatientNote(patientNoteRequest);

        log.info("Controller : addNote - success");

        return noteToSave;

    }
}
