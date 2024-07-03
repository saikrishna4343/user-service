package com.user_service.enums;

public enum AccountStatus {
    ACTIVE("Active"),
    DEACTIVATED("Deactivated");

    private final String description;

    AccountStatus(String description){
        this.description = description;
    }

    public String getDescription(){
        return description;
    }
}
