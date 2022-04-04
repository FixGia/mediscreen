package com.project.ui.ControllerTest;

import com.project.ui.controller.PatientNoteController;
import com.project.ui.dto.PatientNoteRequest;
import com.project.ui.dto.PatientRequest;
import com.project.ui.proxy.PatientHistoricalMicroService;
import com.project.ui.proxy.PatientMicroService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.time.LocalDate;
import java.util.UUID;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@ExtendWith(SpringExtension.class)

public class TestPatientNoteController {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private WebApplicationContext context;

    @MockBean
    PatientHistoricalMicroService microService;

    @MockBean
    PatientMicroService patientMicroService;

    private static PatientNoteRequest patientNoteRequest;

    private static PatientRequest patientRequest;


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


        mvc = MockMvcBuilders.webAppContextSetup(context).build();

    }

    @Test
    public void TestAddNote() throws Exception {

        mvc.perform(MockMvcRequestBuilders.get("/note/add/1"))
                .andExpect(model().attributeExists("note"))
                .andExpect(model().size(1))
                .andExpect(view().name("note/add"))
                .andExpect(status().isOk());
    }

    @Test
    public void TestDeleteNote() throws Exception{

        mvc.perform(MockMvcRequestBuilders.get("/note/delete/" + patientNoteRequest.getId()+ "/1"))
                .andExpect(redirectedUrl("/note/list/1"))
                .andExpect(status().isFound())
                .andExpect(model().hasNoErrors())
                .andExpect(status().is3xxRedirection());
        verify(microService,times(1)).deleteNote(patientNoteRequest.getId());
    }

    @Test
    public void TestDeleteNoteButNull() throws Exception{

        mvc.perform(MockMvcRequestBuilders.get("/note/delete/"))
                .andExpect(redirectedUrl(null))
                .andExpect(status().is(404))
                .andExpect(status().isNotFound());
        verify(microService,times(0)).deleteNote(patientNoteRequest.getId());
    }

    @Test
    public void TestGetNoteList() throws Exception {

        when(patientMicroService.getPatientById(1)).thenReturn(patientRequest);
        mvc.perform(MockMvcRequestBuilders.get("/note/list/1"))
                .andExpect(model().attributeExists("patient"))
                .andExpect(model().attributeExists("notes"))
                .andExpect(model().size(3))
                .andExpect(view().name("note/list"))
                .andExpect(status().isOk());
    }
    @Test
    public void TestUpdatePatientNote() throws Exception {

        when(microService.getNoteById(patientNoteRequest.getId())).thenReturn(patientNoteRequest);

        mvc.perform(MockMvcRequestBuilders.get("/note/update/"+ patientNoteRequest.getId()))
                .andExpect(model().attributeExists("note"))
                .andExpect(model().size(1))
                .andExpect(view().name("note/update"))
                .andExpect(status().isOk());
    }

    @Test
    public void TestGetUpdateNoteForm() throws Exception {

        when(microService.getNoteById(patientNoteRequest.getId())).thenReturn(patientNoteRequest);

        mvc.perform(MockMvcRequestBuilders.post("/note/update/1")
                .sessionAttr("note", patientNoteRequest)
                .param("patientId", patientNoteRequest.getPatientId().toString())
                .param("note", patientNoteRequest.getNote()))
                .andExpect(redirectedUrl("/note/list/1"));

        verify(microService).updateNoteById(anyString(),any(PatientNoteRequest.class));
    }


}
