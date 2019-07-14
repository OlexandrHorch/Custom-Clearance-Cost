package com.customclearancecost.entity;

public enum Age {
    new_vehicle("новий"),
    up_to_five_years_vehicle("до 5-ти років"),
    over_five_years_vehicle("більше 5-ти років");

    Age(String description) {
        this.description = description;
    }

    private String description;

    public String getDescription() {
        return description;
    }

    public static Age fromDescription(String description){
        for(Age value : Age.values()){
            if(value.getDescription().equalsIgnoreCase(description)){
                return value;
            }
        }
        return null;
    }
}