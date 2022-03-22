package com.project.patienthistorical;


import com.project.patienthistorical.dto.PatientNoteRequest;
import com.project.patienthistorical.exception.DataAlreadyExistException;
import com.project.patienthistorical.exception.DataNotFoundException;
import com.project.patienthistorical.model.PatientNote;
import com.project.patienthistorical.repository.PatientNoteRepository;
import com.project.patienthistorical.service.PatientNoteServiceImpl;
import com.project.patienthistorical.util.Mapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@DisplayName("## PatientNoteService from PatientHistorical - UnitTest")
@ExtendWith(MockitoExtension.class)
public class PatientHistoricalServiceTest {

    @InjectMocks
    private PatientNoteServiceImpl patientHistoricalService;

    @Mock
    private PatientNoteRepository patientNoteRepository;

    @Mock
    private Mapper mapper;

    private static PatientNoteRequest patientNoteRequest;
    private static PatientNote patientNote;


    @BeforeEach
    public void setUp() {

        patientNote = new PatientNote(1, LocalDate.now(), "new DoctorNote");
        UUID id = UUID.randomUUID();
        patientNote.setId(id.toString());

        patientNoteRequest = new PatientNoteRequest();
        patientNoteRequest.setPatientId(1);
        patientNoteRequest.setDate(LocalDate.now());
        patientNoteRequest.setNote("DoctorNoteTest");

        List<PatientNote> notes = new ArrayList<>();
        notes.add(patientNote);

        lenient().when(patientNoteRepository.save(patientNote)).thenReturn(patientNote);
        lenient().when(patientNoteRepository.findById(UUID.randomUUID().toString())).thenReturn(Optional.of(patientNote));
        lenient().when(mapper.mapToPatientNoteRequest(patientNote)).thenReturn(patientNoteRequest);
        lenient().when(mapper.mapToPatientNote(patientNoteRequest)).thenReturn(patientNote);
        lenient().when(patientNoteRepository.findByPatientId(1)).thenReturn(notes);

    }

    @Test
    public void testGetPatientNoteForOnePatient() throws DataNotFoundException {

        List<PatientNoteRequest> noteList = patientHistoricalService.getAllNoteBelongPatient(1);
        assertNotNull(noteList);
        assertEquals("new DoctorNote", patientNote.getDoctorNote());

    }

    @Test
    public void testGetPatientNoteForNonePatient() {

        lenient().when(patientNoteRepository.findByPatientId(0)).thenReturn(null);
       assertThrows(DataNotFoundException.class, () -> patientHistoricalService.getAllNoteBelongPatient(0));

    }

    @Test
    public void testAddPatientNote() throws DataAlreadyExistException {

        patientHistoricalService.addPatientNote(patientNoteRequest);

        verify(patientNoteRepository,times(1)).save(patientNote);
    }

    @Test
    public void testAddPatientButAlreadyExist() {

        lenient().when(patientNoteRepository.findPatientNoteByDateAndDoctorNote(any(LocalDate.class), any(String.class))).thenReturn(patientNote);

        assertThrows(DataAlreadyExistException.class, () -> patientHistoricalService.addPatientNote(patientNoteRequest));

    }

    @Test
    public void testUpdatePatient() throws DataNotFoundException {

        lenient().when(patientNoteRepository.findById(patientNote.getId())).thenReturn(Optional.ofNullable(patientNote));
        patientHistoricalService.updatePatientNote(patientNote.getId(),patientNoteRequest);
        assertNotNull(patientNoteRequest);
        assertEquals(patientNote.getDoctorNote(), patientNoteRequest.getNote());
    }

    @Test
    public void testUpdatePatientButIDNotExist() {


        assertThrows(DataNotFoundException.class, () -> patientHistoricalService.updatePatientNote(patientNote.getId(), patientNoteRequest));

    }

    @Test
    public void testDeletePatientNote() throws DataNotFoundException {

        lenient().when(patientNoteRepository.findById(patientNote.getId())).thenReturn(Optional.ofNullable(patientNote));
        patientHistoricalService.deletePatientNote(patientNote.getId());
        verify(patientNoteRepository, times(1)).deleteById(patientNote.getId());

    }

    @Test
    public void testDeletePatientNoteButIDNotExist() {

        assertThrows(DataNotFoundException.class, () -> patientHistoricalService.deletePatientNote(patientNote.getId()));
    }


}
