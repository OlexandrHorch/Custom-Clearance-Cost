package com.customclearancecost.entity;

public enum Owner {
    individual("фізична особа"),
    legal_entity("юридична особа");

    Owner(String description) {
        this.description = description;
    }

    private String description;

    public String getDescription() {
        return description;
    }

    public static Owner fromDescription(String description) {
        for (Owner value : Owner.values()) {
            if (value.getDescription().equalsIgnoreCase(description)) {
                return value;
            }
        }
        return null;
    }
}