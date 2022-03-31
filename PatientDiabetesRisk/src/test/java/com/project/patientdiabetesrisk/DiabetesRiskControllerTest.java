package com.project.patientdiabetesrisk;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.project.patientdiabetesrisk.controller.DiabetesRiskController;
import com.project.patientdiabetesrisk.dto.PatientRequest;
import com.project.patientdiabetesrisk.service.DiabetesRiskService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@DisplayName(" - Controller from Patient MicroService - Unit Test")
@ExtendWith(SpringExtension.class)
@WebMvcTest(DiabetesRiskController.class)
public class DiabetesRiskControllerTest {

    @Autowired
    private MockMvc mvc;


    @Autowired
    private ObjectMapper objectMapper;

    private PatientRequest patientRequest;

    @MockBean
    private DiabetesRiskService diabetesRiskService;

    @BeforeEach
    public void setUp() {

        patientRequest = new PatientRequest(1,
                "lastname",
                "firstname",
                LocalDate.of(1991, 12, 3),
                "M",
                "address",
                "+33689852592");


    }

    @Test
    public void testGetRiskLevel() throws Exception {

        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get("/riskLevel/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()).andReturn();

        assertNotNull(mvcResult);
    }

    @Test
    public void testGetDiabetesAssessment() throws Exception {

        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get("/riskLevel/diabetesAssessment/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()).andReturn();

        assertNotNull(mvcResult);
    }
}
