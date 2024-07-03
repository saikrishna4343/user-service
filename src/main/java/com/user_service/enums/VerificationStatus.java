package com.user_service.enums;

public enum VerificationStatus {
    VERIFIED("Verified"),
    NOT_VERIFIED("Not Verified");

    private final String description;

    VerificationStatus(String description){
        this.description = description;
    }

    public String getDescription(){
        return description;
    }
}
