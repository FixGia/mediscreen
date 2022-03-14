package com.project.patient.UnitTest;


import com.project.patient.dto.PatientRequest;
import com.project.patient.exception.DataAlreadyExistException;
import com.project.patient.exception.DataNotFoundException;
import com.project.patient.model.Patient;
import com.project.patient.repository.PatientRepository;
import com.project.patient.service.PatientServiceImpl;
import com.project.patient.util.Mapper;
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

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@DisplayName("## PatientService from Patient - UnitTest")
@ExtendWith(MockitoExtension.class)
public class PatientServiceTest {

    @InjectMocks
    private PatientServiceImpl patientService;

    @Mock
    private PatientRepository patientRepository;

    @Mock
    private Mapper mapper;

    private static PatientRequest patientRequest;
    private static Patient patient;

    @BeforeEach
    public void setUp(){

        patient = new Patient(1,"lastname2", "firstname1", LocalDate.EPOCH, "M", "address2", "0622334455");


        patientRequest = new PatientRequest();
        patientRequest.setId(1);
        patientRequest.setFirstName("firstname1");
        patientRequest.setLastName("lastname2");
        patientRequest.setAddress("address2");
        patientRequest.setSex("M");
        patientRequest.setPhoneNumber("0622334455");
        patientRequest.setBirthDate(LocalDate.EPOCH);

        List<Patient> patientList = new ArrayList<>();
        patientList.add(patient);

        lenient().when(patientRepository.save(patient)).thenReturn(patient);
        lenient().when(patientRepository.findById(1)).thenReturn(Optional.of(patient));
        lenient().when(mapper.mapToPatientRequest(patient)).thenReturn(patientRequest);
        lenient().when(mapper.mapToPatient(patientRequest)).thenReturn(patient);
        lenient().when(patientRepository.findAll()).thenReturn(patientList);

    }

    @Test
    public void testGetAllPatientsWithKeyWord(){

        List<PatientRequest> patientList = patientService.getAllPatients("keyword");

        assertNotNull(patientList);
    }

    @Test
    public void testGetAllPatientsWithoutKeyWord(){

        List<PatientRequest> patientList = patientService.getAllPatients(null);

        assertNotNull(patientList);
        assertEquals(patientList.get(0).getFirstName(),"firstname1");
    }

    @Test
    public void testGetPatientByID(){

        PatientRequest patient = patientService.getPatientById(1);
        assertNotNull(patient);
        assertEquals(patient.getFirstName(), "firstname1");
    }

    @Test
    public void testGetPatientByIDButNotExist(){

        assertThrows(DataNotFoundException.class, () -> patientService.getPatientById(5));
    }

    @Test
    public void testAddPatient() {

        PatientRequest patient = patientService.addPatient(new PatientRequest(2,"jean", "test", LocalDate.now(), "M", "address", "phoneNumber"));

        verify(patientRepository, times(1)).save(mapper.mapToPatient(patient));
    }

    @Test
    public void testAddPatientButPatientAlreadyExist(){

        lenient().when(patientRepository.findByLastNameAndFirstNameAndBirthDate(patientRequest.getLastName(),patientRequest.getFirstName(),patientRequest.getBirthDate())).thenReturn(patient);

        assertThrows(DataAlreadyExistException.class, () -> patientService.addPatient(patientRequest));
    }

    @Test
    public void testUpdatePatient() {


        patientService.updatePatient(1, new PatientRequest(2,"jean", "test", LocalDate.now(), "M", "address", "phoneNumber"));
        assertNotNull(patientRequest);
        assertEquals(patient.getLastName(), "jean");
    }

    @Test
    public void testUpdatePatientButIdNotExist(){
        assertThrows(DataNotFoundException.class, ()
                ->patientService.updatePatient(5,
                new PatientRequest(2, "jean", "test", LocalDate.now(), "M", "address", "phoneNumber")));
    }

    @Test
    public void testDeletePatient() {

        patientService.deletePatient(1);
        verify(patientRepository,times(1)).deleteById(1);

    }

    @Test
    public void testDeletePatientButIdNotExist(){

        assertThrows(DataNotFoundException.class, () -> patientService.deletePatient(5));

    }



}
