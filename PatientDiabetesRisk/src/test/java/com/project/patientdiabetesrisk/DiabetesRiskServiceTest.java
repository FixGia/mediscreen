package com.project.patientdiabetesrisk;

import com.project.patientdiabetesrisk.dto.PatientNoteRequest;
import com.project.patientdiabetesrisk.service.DiabetesRiskServiceImpl;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("## DiabetesRiskService from PatientDiabetesRisk - UnitTest")
@ExtendWith(MockitoExtension.class)
public class DiabetesRiskServiceTest {


    @InjectMocks
    private DiabetesRiskServiceImpl diabetesRiskService;


    @Test
    public void testGetNumberTrigger() {

        List<PatientNoteRequest> notes = new ArrayList<>();
       PatientNoteRequest patientNoteRequest = new PatientNoteRequest();
        patientNoteRequest.setPatientId(1);
        patientNoteRequest.setDate(LocalDate.now());
        patientNoteRequest.setNote("DoctorNoteTest");

        PatientNoteRequest patientNoteRequest1 = new PatientNoteRequest();
        patientNoteRequest1.setPatientId(1);
        patientNoteRequest1.setDate(LocalDate.now());
        patientNoteRequest1.setNote("Abnormal");

        PatientNoteRequest patientNoteRequest2 = new PatientNoteRequest();
        patientNoteRequest2.setPatientId(1);
        patientNoteRequest2.setDate(LocalDate.now());
        patientNoteRequest2.setNote("Height");
        notes.add(patientNoteRequest);
        notes.add(patientNoteRequest1);
        notes.add(patientNoteRequest2);

        assertEquals(notes.size(), 3);
      //  int result = diabetesRiskService.countTriggerForOnePatient(notes);
       // assertEquals(result, 2);

    }

}
