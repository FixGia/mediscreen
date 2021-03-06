package com.project.patient.dto;


import com.project.patient.constant.Constraints;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import org.hibernate.validator.constraints.Length;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Pattern;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PatientRequest {


    /**
     * the id
     */
    private Integer id;

    /**
     * the lastname
     */
    @NotEmpty(message = "Lastname is mandatory")
    @Length(
            max = Constraints.LAST_NAME_MAX_SIZE,
            message = "The maximum length for lastName is 125 characters")
    private String lastName;

    /**
     * the firstname
     */
    @NotEmpty(message = "Firstname is mandatory")
    @Length(
            max = Constraints.FIRST_NAME_MAX_SIZE,
            message = "The maximum length for firstName is 125 characters")
    private String firstName;

    /**
     * the birthDate
     */
    @NotNull(message = "Date of birth is mandatory")
    @Past(message = "Please enter a valid birthdate")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate birthDate;

    /**
     * The sex
     */
    @NotEmpty(message = "Sex is mandatory")
    @Length(
            max = Constraints.SEX_MAX_SIZE,
            message = "The maximum length for sex is 1 characters")
    @Pattern(
            regexp = "^[M|F]$",
            message = "Please enter character M,F")
    private String sex;

    /**
     * the address
     */
    @Length(max = Constraints.ADDRESS_MAX_SIZE,
            message = "The maximum length for address is 150 characters")
    private String address;

    /**
     * the phoneNumber
     */
    @Length(max = Constraints.PHONE_MAX_SIZE, message = "Please enter a valid phone number")
    @Pattern(regexp = Constraints.PHONE_NUMBER_PATTERN , message = "Please Enter a valid phone number")
    private String phoneNumber;


}