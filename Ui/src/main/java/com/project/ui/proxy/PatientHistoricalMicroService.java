package com.project.ui.proxy;


import com.project.ui.dto.PatientNoteRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import java.util.List;

@FeignClient(value ="patient-historical-microService",  url = "${PROXY_PATIENT_HISTORICAL:http://localhost:9012/note}")
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

    /**
     * Method to get PatientNote from PatientHistorical MicroService
     *
     * @param noteId the note id
     *
     * @return a note
     */
    @GetMapping({"/get/{id}"})
    PatientNoteRequest getNoteById(@PathVariable("id") String noteId);

    /**
     * Method to update patientNote from PatientHistoricalMicroService
     *
     * @param noteId the note id
     *
     * @param patientNoteRequest the patientNoteRequest
     *
     * @return updated patientNote
     */
    @PostMapping({"/update/{id}"})
    PatientNoteRequest updateNoteById(@PathVariable("id") String noteId, PatientNoteRequest patientNoteRequest);

    /**
     * Method to add a patientNote from PatientHistoricalMicroService
     *
     * @param patientNoteRequest the patientNote
     *
     * @return a new PatientNote
     */
    @PostMapping({"/add"})
    PatientNoteRequest addNote(PatientNoteRequest patientNoteRequest);

    /**
     * Method to delete a patientNote from PatientHistoricalMicroService
     *
     * @param id the note id
     *
     */
    @GetMapping({"/delete/{id}"})
    void deleteNote(@PathVariable String id);

}
