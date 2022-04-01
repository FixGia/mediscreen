package com.project.patientdiabetesrisk;

import com.project.patientdiabetesrisk.dto.PatientRequest;
import com.project.patientdiabetesrisk.service.DiabetesRiskService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

import java.time.LocalDate;

@SpringBootApplication
@EnableFeignClients
public class PatientDiabetesRiskApplication {



    public static void main(String[] args) {
        SpringApplication.run(PatientDiabetesRiskApplication.class, args);
    }


}
