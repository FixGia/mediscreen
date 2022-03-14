package com.project.patient.exception;


/**
 * Data Already Exists Class
 *
 * @author FixGia
 */
public class DataAlreadyExistException extends RuntimeException{

    /**
     * DataAlreadyExistMethod
     *
     * @param message the error message;
     */
    public DataAlreadyExistException(final String message) {
        super(message);
    }
}