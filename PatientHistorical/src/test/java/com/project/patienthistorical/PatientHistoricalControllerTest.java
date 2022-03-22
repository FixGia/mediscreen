package com.project.patienthistorical;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.project.patienthistorical.controller.PatientHistoricalController;
import com.project.patienthistorical.dto.PatientNoteRequest;
import com.project.patienthistorical.model.PatientNote;
import com.project.patienthistorical.service.PatientNoteService;
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
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@DisplayName(" - Controller from Patient Historical - Unit Test")
@ExtendWith(SpringExtension.class)
@WebMvcTest(PatientHistoricalController.class)
public class PatientHistoricalControllerTest {

    @Autowired
    private MockMvc mvc;


    @Autowired
    private ObjectMapper objectMapper;

    private PatientNoteRequest patientNoteRequest;

    @MockBean
    private PatientNoteService patientNoteService;

    @BeforeEach
    public void setUp() {

        UUID uuid = UUID.randomUUID();
        String ID = uuid.toString();
        patientNoteRequest = new PatientNoteRequest(ID,1,LocalDate.now(),"new note");

    }

    @Test
    public void TestGetAllNoteBelongPatient() throws Exception {

        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get("/note/list/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()).andReturn();

        assertNotNull(mvcResult);
    }

    @Test
    public void TestDeleteNote() throws Exception {

        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get("/note/delete/"+ patientNoteRequest.getId())
                .contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk()).andReturn();

        assertNotNull(mvcResult);
    }

    @Test
    public void TestUpdateNote() throws Exception {

        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post("/note/update/"+ patientNoteRequest.getId())
                .content(objectMapper.writeValueAsString(patientNoteRequest))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()).andReturn();

        assertNotNull(mvcResult);
    }

    @Test
    public void TestAddNote() throws Exception {

        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post("/note/add")
                .content(objectMapper.writeValueAsString(patientNoteRequest))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated()).andReturn();

        assertNotNull(mvcResult);
    }


}
