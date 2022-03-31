package com.project.patient.repository;


import com.project.patient.model.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface PatientRepository extends JpaRepository<Patient, Integer> {

    /**
     * The method to find by Key word in DB
     *
     * @param keyword the keyword
     *
     * @return a list with patients
     */
    @Query("SELECT p FROM Patient p WHERE lower(CONCAT(p.lastName,' ', p.firstName)) LIKE %?1%")
    List<Patient> findByKeyword(String keyword);


    /**
     * Method to find patient by lastname, firstname and birthDate
     *
     * @param lastName the firstname
     * @param firstName the lastname
     * @param birthDate the birthDate
     *
     * @return a Patient
     */
    Patient findByLastNameAndFirstNameAndBirthDate(final String lastName, final String firstName, final LocalDate birthDate);

}
