package com.project.ui.ControllerTest;

import com.project.ui.dto.PatientRequest;
import com.project.ui.proxy.PatientMicroService;
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

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class TestPatientController {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private WebApplicationContext context;

    @MockBean
    PatientMicroService patientMicroService;

    private static PatientRequest patientRequest;

    @BeforeEach
    public void contextLoads(){

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
    public void TestAddPatient() throws Exception{


        mvc.perform(MockMvcRequestBuilders.get("/patient/add"))
                .andExpect(model().attributeExists("patientRequest"))
                .andExpect(model().size(1))
                .andExpect(view().name("patient/add"))
                .andExpect(status().isOk());
    }

    @Test
    public void TestDeletePatient() throws Exception {

        mvc.perform(MockMvcRequestBuilders.get("/patient/delete/1"))
                .andExpect(redirectedUrl("/patient/list"))
                .andExpect(status().isFound())
                .andExpect(model().hasNoErrors())
                .andExpect(status().is3xxRedirection());
        verify(patientMicroService,times(1)).deletePatient(1);

    }

    @Test
    public void TestDeletePatientButNull() throws Exception {

        mvc.perform(MockMvcRequestBuilders.get("/patient/delete/"))
                .andExpect(redirectedUrl(null))
                .andExpect(status().is(404))
                .andExpect(status().isNotFound());
        verify(patientMicroService,times(0)).deletePatient(1);
    }

    @Test
    public void TestGetPatientList() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/patient/list"))
                .andExpect(model().attributeExists("patients"))
                .andExpect(model().size(2))
                .andExpect(view().name("patient/list"))
                .andExpect(status().isOk());
    }

    @Test
    public void TestGetPatientListButEmpty() throws Exception {

        mvc.perform(MockMvcRequestBuilders.get("/patient/list"))
                .andExpect(model().size(2))
                .andExpect(view().name("patient/list"))
                .andExpect(status().isOk());

        verify(patientMicroService, times(1)).getPatientList(null);

    }

    @Test
    public void TestUpdatePatient() throws Exception {

        when(patientMicroService.getPatientById(1)).thenReturn(patientRequest);
        mvc.perform(MockMvcRequestBuilders.get("/patient/update/1"))
                .andExpect(model().attributeExists("patientRequest"))
                .andExpect(model().size(1))
                .andExpect(view().name("patient/update"))
                .andExpect(status().isOk());
    }


}
