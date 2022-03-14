package com.project.patient.constant;
import lombok.NoArgsConstructor;



@NoArgsConstructor
public class Constraints {

    public static final int FIRST_NAME_MAX_SIZE = 100;


    public static final int LAST_NAME_MAX_SIZE = 100;


    public static final int SEX_MAX_SIZE = 1;


    public static final int ADDRESS_MAX_SIZE = 100;


    public static final int PHONE_MAX_SIZE = 15;

    public static final String PHONE_NUMBER_PATTERN = "^(\\+\\d{1,3}( )?)?((\\(\\d{3}\\))|\\d{3})[- .]?\\d{3}[- .]?\\d{4}$";

}