package com.project.patient;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class PatientApplication {

    /**
     * The main method to start main method.
     *
     * @param args the arguments
     */
    public static void main(final String[] args) {
        SpringApplication.run(PatientApplication.class, args);
    }

}