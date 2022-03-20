package com.project.patienthistorical.exception;

/**
 * Data Not Found Exception Class
 *
 * @author FixGia
 *
 */
public class DataNotFoundException extends Exception {


    /**
     * Data Not Found Exception Method
     *
     * @param message the message;
     *
     */
    public DataNotFoundException(final String message) {
        super(message);
    }

}