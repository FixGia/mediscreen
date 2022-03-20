package com.project.patienthistorical.exception;

/**
 * NotConformDataException Class
 *
 * @author FixGia
 *
 */

public class NotConformDataException extends Exception{
    /**
     * NotConformDataException Method
     *
     * @param message the error message;
     */
    public NotConformDataException(final String message){

        super(message);
    }
}