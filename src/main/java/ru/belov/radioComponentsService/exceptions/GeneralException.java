package ru.belov.radioComponentsService.exceptions;

/**
 * @author Vladimir Krasnov
 */
public class GeneralException extends GlobalAppException{
    public GeneralException(int status, String message) {
        super(status, message);
    }
}
