package com.project.patientdiabetesrisk;

import com.project.patientdiabetesrisk.dto.PatientNoteRequest;
import com.project.patientdiabetesrisk.dto.PatientRequest;
import com.project.patientdiabetesrisk.proxy.PatientHistoricalMicroService;
import com.project.patientdiabetesrisk.proxy.PatientMicroService;
import com.project.patientdiabetesrisk.service.DiabetesRiskServiceImpl;
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

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.lenient;

@DisplayName("## DiabetesRiskService from PatientDiabetesRisk - UnitTest")
@ExtendWith(MockitoExtension.class)
public class DiabetesRiskServiceTest {


    @InjectMocks
    private DiabetesRiskServiceImpl diabetesRiskService;


    @Mock
    PatientMicroService patientMicroService;
    @Mock
    PatientHistoricalMicroService patientHistoricalMicroService;

    private static List<PatientNoteRequest> notes;
    private static PatientRequest patientRequest;
    private static PatientNoteRequest note1;
    private static PatientNoteRequest note2;
    private static PatientNoteRequest note3;

    @BeforeEach
    public void setUp() {

        patientRequest = new PatientRequest(1, "lastname",
                "firtname",
                LocalDate.of(1991, 12, 12),
                "M",
                "12 rue du test",
                "+33694789639");
        note1 = new PatientNoteRequest();
        note1.setPatientId(1);
        note1.setDate(LocalDate.now());
        note1.setNote("DoctorNoteTest");

        note2 = new PatientNoteRequest();
        note2.setPatientId(1);
        note2.setDate(LocalDate.now());
        note2.setNote("Abnormal");

        note3 = new PatientNoteRequest();
        note3.setPatientId(1);
        note3.setDate(LocalDate.now());
        note3.setNote("Height");

        notes = new ArrayList<>();
        notes.add(note1);
        notes.add(note3);
        notes.add(note2);

    }

    @DisplayName(" Service - Evaluate Risk Level "+
    " If Patient age >30 "+
    " Patient sex = M " +
    " Patient have 2 triggers " +
    " The result expected is Borderline")
    @Test
    public void testEvaluateRiskLevelAndTextResultIsBorderline() {


        assertEquals(notes.size(), 3);

        lenient().when(patientMicroService.getPatientById(1)).thenReturn(patientRequest);
        lenient().when(patientHistoricalMicroService.getPatientNoteList(1)).thenReturn(notes);
        String result = diabetesRiskService.finalRiskLevelTextResult(1);

        assertEquals("Patient : lastname firtname. Age: 31  diabetes risk level is : Borderline", result);

    }

    @DisplayName(" Service - Evaluate Risk Level "+
            " If Patient age > 30 "+
            " Patient sex = M " +
            " Patient have 2 triggers or less " +
            " The result expected is None")
    @Test
    public void testEvaluateRiskLevelAndTextResultIsNone() {

        notes.clear();


        lenient().when(patientMicroService.getPatientById(1)).thenReturn(patientRequest);
        lenient().when(patientHistoricalMicroService.getPatientNoteList(1)).thenReturn(notes);
        String result = diabetesRiskService.finalRiskLevelTextResult(1);

        assertEquals("Patient : lastname firtname. Age: 31  diabetes risk level is : None", result);

    }
    @DisplayName(" Service - Evaluate Risk Level "+
            " If Patient age > 30 "+
            " Patient sex = M " +
            " Patient have 6 triggers or more but less 8 " +
            " The result expected is In Danger")
    @Test
    public void testEvaluateRiskLevelAndTextResultIsInDanger() {

        note1.setNote("Abnormal,Height,Weight,Smoker,");

        lenient().when(patientMicroService.getPatientById(1)).thenReturn(patientRequest);
        lenient().when(patientHistoricalMicroService.getPatientNoteList(1)).thenReturn(notes);
        String result = diabetesRiskService.finalRiskLevelTextResult(1);

        assertEquals("Patient : lastname firtname. Age: 31  diabetes risk level is : In danger", result);
    }

    @DisplayName(" Service - Evaluate Risk Level "+
            " If Patient age >30 "+
            " Patient sex = M " +
            " Patient have 8 triggers or more" +
            " The result expected is Early onset")
    @Test
    public void testEvaluateRiskLevelAndTextResultIsEarlyOnSet() {

        note1.setNote("Abnormal,Height,Weight,Smoker,Microalbumin,Cholesterol");

        lenient().when(patientMicroService.getPatientById(1)).thenReturn(patientRequest);
        lenient().when(patientHistoricalMicroService.getPatientNoteList(1)).thenReturn(notes);
        String result = diabetesRiskService.finalRiskLevelTextResult(1);

        assertEquals("Patient : lastname firtname. Age: 31  diabetes risk level is : Early onset", result);
    }

    @DisplayName(" Service - Evaluate Risk Level "+
            " If Patient age <30 "+
            " Patient sex = F " +
            " Patient have 6 triggers " +
            " The result expected is In Danger")
    @Test
    public void testEvaluateRiskLevelWithDifferentParams() {

        patientRequest.setSex("F");
        patientRequest.setBirthDate(LocalDate.of(2011, 12, 12));
        note1.setNote("Abnormal,Height,Weight,Smoker");

        lenient().when(patientMicroService.getPatientById(1)).thenReturn(patientRequest);
        lenient().when(patientHistoricalMicroService.getPatientNoteList(1)).thenReturn(notes);
        String result = diabetesRiskService.finalRiskLevelTextResult(1);

        assertEquals("Patient : lastname firtname. Age: 11  diabetes risk level is : In danger", result);

    }

    @DisplayName(" Service - Evaluate Risk Level "+
            " If Patient age <30 "+
            " Patient sex = M " +
            " Patient have 6 triggers " +
            " The result expected is Early onset")
    @Test
    public void testEvaluateRiskLevelAndChangePatientSex() {

        patientRequest.setSex("M");
        patientRequest.setBirthDate(LocalDate.of(2011, 12, 12));
        note1.setNote("Abnormal,Height,Weight,Smoker");

        lenient().when(patientMicroService.getPatientById(1)).thenReturn(patientRequest);
        lenient().when(patientHistoricalMicroService.getPatientNoteList(1)).thenReturn(notes);
        String result = diabetesRiskService.finalRiskLevelTextResult(1);

        assertEquals("Patient : lastname firtname. Age: 11  diabetes risk level is : Early onset", result);

    }

    @DisplayName(" Service - Evaluate Risk Level "+
            " If Patient age <30 "+
            " Patient sex = M " +
            " Patient have 2 triggers " +
            " The result expected is None")
    @Test
    public void testEvaluateRiskLevelWithPatientAge11(){

        patientRequest.setSex("M");
        patientRequest.setBirthDate(LocalDate.of(2011, 12, 12));


        lenient().when(patientMicroService.getPatientById(1)).thenReturn(patientRequest);
        lenient().when(patientHistoricalMicroService.getPatientNoteList(1)).thenReturn(notes);
        String result = diabetesRiskService.finalRiskLevelTextResult(1);

        assertEquals("Patient : lastname firtname. Age: 11  diabetes risk level is : None", result);
    }


}
