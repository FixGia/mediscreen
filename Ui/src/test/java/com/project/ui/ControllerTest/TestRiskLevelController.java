package com.project.ui.ControllerTest;

import com.project.ui.dto.DiabetesAssessment;
import com.project.ui.dto.PatientNoteRequest;
import com.project.ui.dto.PatientRequest;
import com.project.ui.proxy.DiabetesRiskLevelMicroService;
import com.project.ui.proxy.PatientHistoricalMicroService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.time.LocalDate;
import java.util.UUID;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class TestRiskLevelController {


    @Autowired
    private MockMvc mvc;

    @Autowired
    private WebApplicationContext context;

    @MockBean
    DiabetesRiskLevelMicroService diabetesRiskLevelMicroService;

    private static PatientNoteRequest patientNoteRequest;

    private static PatientRequest patientRequest;

    private static DiabetesAssessment diabetesAssessment;


    @BeforeEach
    public void contextLoads(){

        patientNoteRequest = new PatientNoteRequest();
        patientNoteRequest.setPatientId(1);
        patientNoteRequest.setNote("new note");
        UUID id = UUID.randomUUID();
        patientNoteRequest.setId(id.toString());
        patientNoteRequest.setDate(LocalDate.now());

        patientRequest = new PatientRequest();
        patientRequest.setId(1);
        patientRequest.setFirstName("firstname1");
        patientRequest.setLastName("lastname2");
        patientRequest.setAddress("address2");
        patientRequest.setSex("M");
        patientRequest.setPhoneNumber("0622334455");
        patientRequest.setBirthDate(LocalDate.EPOCH);

        diabetesAssessment = new DiabetesAssessment();
        diabetesAssessment.setPatientFirstName("firstname1");
        diabetesAssessment.setPatientLastName("lastname1");
        diabetesAssessment.setPatientAge("20");
        diabetesAssessment.setRiskLevels("In danger");

        mvc = MockMvcBuilders.webAppContextSetup(context).build();

    }

    @Test
    public void TestShowRiskLevel() throws Exception {

        when(diabetesRiskLevelMicroService.getDiabetesAssessment(1)).thenReturn(diabetesAssessment);

        mvc.perform(MockMvcRequestBuilders.get("/riskLevel/1"))
                .andExpect(model().attributeExists("riskLevel"))
                .andExpect(model().size(2))
                .andExpect(view().name("assessment/riskLevel"))
                .andExpect(status().isOk());
    }

}
