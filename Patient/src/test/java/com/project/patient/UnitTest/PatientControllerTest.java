package com.project.patient.UnitTest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.project.patient.controller.PatientController;
import com.project.patient.dto.PatientRequest;
import com.project.patient.service.PatientService;
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
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.lenient;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@DisplayName(" - Controller from Patient MicroService - Unit Test")
@ExtendWith(SpringExtension.class)
@WebMvcTest(PatientController.class)
public class PatientControllerTest {

    @Autowired
    private MockMvc mvc;


    @Autowired
    private ObjectMapper objectMapper;

    private PatientRequest patientRequest;

    @MockBean
    private PatientService patientService;


    @BeforeEach
    public void setUp() {

       patientRequest = new PatientRequest(1,
               "lastname",
               "firstname",
               LocalDate.of(1991,12, 3),
               "M",
               "address",
               "+33689852592");


    }

    @Test
    public void TestGetAllPatients() throws Exception {

        lenient().when(patientService.getAllPatients("keyword")).thenReturn(List.of());
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get("/patient/list").param("keyword", "keyword")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()).andReturn();

        assertNotNull(mvcResult);
    }

    @Test
    public void TestGetPatient() throws Exception {


        lenient().when(patientService.getPatientById(1)).thenReturn(new PatientRequest());
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get("/patient/get/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()).andReturn();

        assertNotNull(mvcResult);
    }

    @Test
    public void TestUpdatePatient() throws Exception {


        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post("/patient/update/1").content(objectMapper.writeValueAsString(patientRequest))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()).andReturn();

        assertNotNull(mvcResult);
    }

    @Test
    public void TestDeletePatient() throws Exception {

        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get("/patient/delete/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()).andReturn();

        assertNotNull(mvcResult);
    }

    @Test
    public void AddPatient() throws Exception {

        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post("/patient/add").content(objectMapper.writeValueAsString(patientRequest))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated()).andReturn();

        assertNotNull(mvcResult);
    }


}
