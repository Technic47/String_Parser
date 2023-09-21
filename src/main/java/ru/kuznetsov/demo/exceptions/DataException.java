package ru.kuznetsov.demo.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class DataException extends RuntimeException {
    public DataException() {
        super("Data string is too long!");
    }

    public DataException(String message){
        super(message);
    }

    public DataException(Throwable cause) {
        super(cause);
    }
}
