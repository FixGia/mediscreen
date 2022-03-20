package com.project.patienthistorical.exception;


/**
 * Data Already Exists Class
 *
 * @author FixGia
 */
public class DataAlreadyExistException extends Exception{

    /**
     * DataAlreadyExistMethod
     *
     * @param message the error message;
     */
    public DataAlreadyExistException(final String message) {
        super(message);
    }
}