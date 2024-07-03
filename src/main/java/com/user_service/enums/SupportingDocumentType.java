package com.user_service.enums;

public enum SupportingDocumentType {
    DL("Driving License"),
    PASSPORT("Passport"),
    STATE_ID("State Id"),
    WORK_AUTH("Work Authorization"),
    OTHER("Other");

    private final String description;

     SupportingDocumentType(String description){
        this.description = description;
    }

    public String getDescription(){
        return this.description;
    }
}
