package com.project.patienthistorical.service;

import com.project.patienthistorical.dto.PatientNoteRequest;
import com.project.patienthistorical.exception.DataAlreadyExistException;
import com.project.patienthistorical.exception.DataNotFoundException;
import com.project.patienthistorical.model.PatientNote;
import com.project.patienthistorical.repository.PatientNoteRepository;
import com.project.patienthistorical.util.Mapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
@Slf4j
public class PatientNoteServiceImpl implements PatientNoteService {


    private final PatientNoteRepository patientNoteRepository;
    private final Mapper mapper;


    public PatientNoteServiceImpl(PatientNoteRepository patientNoteRepository, Mapper mapper) {
        this.patientNoteRepository = patientNoteRepository;
        this.mapper = mapper;
    }

    /**
     * Method to getPatient Note
     *
     * @param noteId The note ID
     *
     * @return a patientNote
     */
    public PatientNoteRequest getPatientNote(String noteId) throws DataNotFoundException {

        log.debug("Service : get PatientNote - supply");

        Optional<PatientNote> patientNote = patientNoteRepository.findById(noteId);

        if (patientNote.isPresent()) {

            log.info("Service : get PatientNote - submit");

            return mapper.mapToPatientNoteRequest(patientNote.get());
        }

        throw new DataNotFoundException("patientNote doesn't exist");
    }

    /**
     * Method to add a patientNote
     *
     * @param patientNoteRequest the PatientNote to add
     *
     * @return a patientRequest, image of patientNote which added
     *
     * @throws DataAlreadyExistException The patientNote already exist
     *
     */
    public PatientNoteRequest addPatientNote(PatientNoteRequest patientNoteRequest) throws DataAlreadyExistException {

        log.debug("Service : add PatientNote - supply");

PatientNote patientNote = patientNoteRepository.findPatientNoteByDateAndDoctorNote(patientNoteRequest.getDate(), patientNoteRequest.getNote());

            if (patientNote == null ) {

                //** Date generated here **//
                patientNoteRequest.setDate(LocalDate.now());
                PatientNote patientToSave = patientNoteRepository.save(mapper.mapToPatientNote(patientNoteRequest));

                log.info("Service : addPatientNote - success");
                return mapper.mapToPatientNoteRequest(patientToSave);
            }

            else throw new DataAlreadyExistException("The patientNote already exist");
    }

    /**
     * Method to delete PatientNote
     *
     * @param noteId The note ID
     *
     * @throws DataNotFoundException The patientNote doesn't exist
     *
     *
     */
    public void deletePatientNote(String noteId) throws DataNotFoundException {

        log.debug("Service : delete PatientNote - supply");

        Optional<PatientNote> patientNote = patientNoteRepository.findById(noteId);

        if (patientNote.isPresent()) {

            log.info("Service : delete PatientNote - success");

            patientNoteRepository.deleteById(noteId);
        }
        else throw new DataNotFoundException("The patientNote doesn't exist");
    }

    /**
     * Method to Update Note Patient
     *
     * @param noteId The Note id
     *
     * @param patientNoteRequest The Body of NoteRequest
     *
     * @throws DataNotFoundException The patientNote doesn't exist
     */
    public PatientNoteRequest updatePatientNote(final String noteId, final PatientNoteRequest patientNoteRequest) throws DataNotFoundException {

        log.debug("Service : update PatientNote - supply");

        Optional<PatientNote> patientNote = patientNoteRepository.findById(noteId);

        if (patientNote.isPresent()) {


            PatientNote patientToUpdate = patientNote.get();
            patientToUpdate.setId(noteId);
            patientToUpdate.setPatientId(patientNoteRequest.getPatientId());
            patientToUpdate.setDate(patientNoteRequest.getDate());
            patientToUpdate.setDoctorNote(patientNoteRequest.getNote());
            patientNoteRepository.save(patientToUpdate);

            log.info("Service : update PatientNote - success");

            return patientNoteRequest;

        }

        throw new DataNotFoundException("The PatientNote doesn't exist");
    }

    /**
     * Method to get ALlNote for a Patient
     *
     * @param patientId the patient ID
     *
     * @return all patientNote for one patient
     *
     * @throws DataNotFoundException The patientId doesn't exist
     *
     */
    public List<PatientNoteRequest> getAllNoteBelongPatient(Integer patientId) throws DataNotFoundException {

        log.debug("Service : get All PatientNote For one Patient - supply");
        List<PatientNote> notes = patientNoteRepository.findByPatientId(patientId);

        if (notes != null) {

            log.info("Service : get All PatientNote For one Patient - success");

            return notes.stream().map(mapper::mapToPatientNoteRequest).collect(Collectors.toList());

        }
        else throw new DataNotFoundException("patient Id doesn't exist");
    }
}
