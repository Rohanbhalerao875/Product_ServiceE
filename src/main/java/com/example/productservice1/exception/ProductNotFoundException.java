package com.example.productservice1.exception;



public class ProductNotFoundException extends Exception {
    private long id;
    private String Message;

    public ProductNotFoundException(long id, String Message) {
        super(Message);
        this.id = id;
        this.Message = Message;
    }

    @Override
    public String getMessage() {
        return Message;
    }

    public void setMessage(String message) {
        Message = message;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
