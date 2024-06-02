package ru.belov.radioComponentsService.exceptions;

public class GeneralException extends GlobalAppException{
    public GeneralException(int status, String message) {
        super(status, message);
    }
}
