package es.udc.paproject.backend.model.exceptions;

import java.time.LocalDate;

public class InvalidSearchDateException extends Exception{

    private LocalDate date;

    public InvalidSearchDateException(String message) {
        super(message);
    }

    public InvalidSearchDateException(LocalDate date){
        this.date = date;
    }

    public LocalDate getDate() {
        return date;
    }
}
