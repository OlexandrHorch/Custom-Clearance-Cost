package com.customclearancecost.entity;

public enum EngineType {
    petrol("бензиновий"),
    diesel("дизельний"),
    electric("електритний");

    EngineType(String description) {
        this.description = description;
    }

    private String description;

    public String getDescription() {
        return description;
    }
}