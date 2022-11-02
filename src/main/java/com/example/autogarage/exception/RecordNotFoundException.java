package com.example.autogarage.exception;

public class RecordNotFoundException extends RuntimeException{

    private static final long serialVersionUID = 1L; // waarom hebben we een serial version uid nodig?

    public RecordNotFoundException() {
        super();
    }

    public RecordNotFoundException(String message) {
        super(message);
    }
}
