package com.project.patient.exception;

/**
 * Data Not Found Exception Class
 *
 * @author FixGia
 *
 */
public class DataNotFoundException extends RuntimeException {


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