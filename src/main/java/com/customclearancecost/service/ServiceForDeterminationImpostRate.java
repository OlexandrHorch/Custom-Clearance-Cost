package com.customclearancecost.service;

import com.customclearancecost.entity.Vehicle;

public class ServiceForDeterminationImpostRate {
    // Method for determination impost rate by owner
    public void determinationImpostRateByOwner(Vehicle vehicle) {
        if (vehicle.getOwner().name().equals("individual")) {
            vehicle.setImpostRate(10);

        } else if (vehicle.getOwner().name().equals("legal_entity")) {
            determinationImpostRateByCountry(vehicle);
        }
    }


    // Method for determination impost rate by country
    private void determinationImpostRateByCountry(Vehicle vehicle) {
        if (vehicle.getCountryOfOrigin().name().equals("AUSTRIA") ||
                vehicle.getCountryOfOrigin().name().equals("BELGIUM") ||
                vehicle.getCountryOfOrigin().name().equals("BULGARIA") ||
                vehicle.getCountryOfOrigin().name().equals("THE_UNITED_KINGDOM") ||
                vehicle.getCountryOfOrigin().name().equals("GREECE") ||
                vehicle.getCountryOfOrigin().name().equals("DANIEL") ||
                vehicle.getCountryOfOrigin().name().equals("ESTONIA") ||
                vehicle.getCountryOfOrigin().name().equals("IRELAND") ||
                vehicle.getCountryOfOrigin().name().equals("SPAIN") ||
                vehicle.getCountryOfOrigin().name().equals("ITALY") ||
                vehicle.getCountryOfOrigin().name().equals("CYPRUS") ||
                vehicle.getCountryOfOrigin().name().equals("LATVIA") ||
                vehicle.getCountryOfOrigin().name().equals("LITHUANIA") ||
                vehicle.getCountryOfOrigin().name().equals("LUXEMBOURG") ||
                vehicle.getCountryOfOrigin().name().equals("MALTA") ||
                vehicle.getCountryOfOrigin().name().equals("NETHERLANDS") ||
                vehicle.getCountryOfOrigin().name().equals("POLAND") ||
                vehicle.getCountryOfOrigin().name().equals("PORTUGAL") ||
                vehicle.getCountryOfOrigin().name().equals("ROMANIA") ||
                vehicle.getCountryOfOrigin().name().equals("SLOVAKIA") ||
                vehicle.getCountryOfOrigin().name().equals("SLOVENIA") ||
                vehicle.getCountryOfOrigin().name().equals("HUNGARY") ||
                vehicle.getCountryOfOrigin().name().equals("FINLAND") ||
                vehicle.getCountryOfOrigin().name().equals("FRANCE") ||
                vehicle.getCountryOfOrigin().name().equals("CROATIA") ||
                vehicle.getCountryOfOrigin().name().equals("CZECH_REPUBLIC") ||
                vehicle.getCountryOfOrigin().name().equals("SWEDEN")) {
            determinationImpostRateByAgeForEU(vehicle);

        } else if (vehicle.getCountryOfOrigin().name().equals("NOT_DEFINED")) {
            determinationImpostRateByEngineTypeNotDefinedCountry(vehicle);

        } else {
            determinationImpostRateByAgeForOtherCountry(vehicle);
        }
    }


    // Method for determination impost rate by age for EU
    private void determinationImpostRateByAgeForEU(Vehicle vehicle) {
        if (vehicle.getAge().name().equals("new_vehicle")) {
            determinationImpostRateByEngineTypeAndCapacityVehicleNewForEU(vehicle);

        } else if (vehicle.getAge().name().equals("up_to_five_years_vehicle") ||
                vehicle.getAge().name().equals("over_five_years_vehicle")) {
            determinationImpostRateByEngineTypeAndCapacityVehicleUsedForEU(vehicle);
        }
    }

    // Method for determination impost rate by engine type and capacity if vehicle new
    private void determinationImpostRateByEngineTypeAndCapacityVehicleNewForEU(Vehicle vehicle) {
        if (vehicle.getEngineType().name().equals("petrol")) {
            if (vehicle.getCapacity() <= 1000) {
                vehicle.setImpostRate(5);
            } else if (vehicle.getCapacity() > 1000 && vehicle.getCapacity() <= 1500) {
                vehicle.setImpostRate(6.4);
            } else if (vehicle.getCapacity() > 1500 && vehicle.getCapacity() <= 2200) {
                vehicle.setImpostRate(6.4);
            } else if (vehicle.getCapacity() > 2200 && vehicle.getCapacity() <= 3000) {
                vehicle.setImpostRate(5);
            } else if (vehicle.getCapacity() > 3000) {
                vehicle.setImpostRate(4.5);
            }
        } else if (vehicle.getEngineType().name().equals("diesel")) {
            if (vehicle.getCapacity() <= 1500) {
                vehicle.setImpostRate(6.4);
            } else if (vehicle.getCapacity() > 1500 && vehicle.getCapacity() <= 2500) {
                vehicle.setImpostRate(6.4);
            } else if (vehicle.getCapacity() > 2500) {
                vehicle.setImpostRate(5);
            }
        } else if (vehicle.getEngineType().name().equals("electric")) {
            vehicle.setImpostRate(0);
        }
    }

    // Method for determination impost rate by engine type and capacity if vehicle used
    private void determinationImpostRateByEngineTypeAndCapacityVehicleUsedForEU(Vehicle vehicle) {
        if (vehicle.getEngineType().name().equals("petrol")) {
            if (vehicle.getCapacity() <= 1000) {
                vehicle.setImpostRate(6.4);
            } else if (vehicle.getCapacity() > 1000 && vehicle.getCapacity() <= 1500) {
                vehicle.setImpostRate(6.4);
            } else if (vehicle.getCapacity() > 1500 && vehicle.getCapacity() <= 2200) {
                vehicle.setImpostRate(6.4);
            } else if (vehicle.getCapacity() > 2200 && vehicle.getCapacity() <= 3000) {
                vehicle.setImpostRate(6.4);
            } else if (vehicle.getCapacity() > 3000) {
                vehicle.setImpostRate(6.4);
            }
        } else if (vehicle.getEngineType().name().equals("diesel")) {
            if (vehicle.getCapacity() <= 1500) {
                vehicle.setImpostRate(6.4);
            } else if (vehicle.getCapacity() > 1500 && vehicle.getCapacity() <= 2500) {
                vehicle.setImpostRate(6.4);
            } else if (vehicle.getCapacity() > 2500) {
                vehicle.setImpostRate(6.4);
            }
        } else if (vehicle.getEngineType().name().equals("electric")) {
            vehicle.setImpostRate(0);
        }
    }


    // Method for determination impost rate by engine type for not defined country
    private void determinationImpostRateByEngineTypeNotDefinedCountry(Vehicle vehicle) {
        if (vehicle.getEngineType().name().equals("petrol") || vehicle.getEngineType().name().equals("diesel")) {
            vehicle.setImpostRate(10);
        } else {
            vehicle.setImpostRate(0);
        }
    }


    // Method for determination impost rate by age for other country
    private void determinationImpostRateByAgeForOtherCountry(Vehicle vehicle) {
        if (vehicle.getAge().name().equals("new_vehicle")) {
            determinationImpostRateByEngineTypeAndCapacityVehicleNew(vehicle);

        } else if (vehicle.getAge().name().equals("up_to_five_years_vehicle") ||
                vehicle.getAge().name().equals("over_five_years_vehicle")) {
            determinationImpostRateByEngineTypeAndCapacityVehicleUsed(vehicle);
        }
    }

    // Method for determination impost rate by engine type and capacity if vehicle new
    private void determinationImpostRateByEngineTypeAndCapacityVehicleNew(Vehicle vehicle) {
        if (vehicle.getEngineType().name().equals("petrol")) {
            if (vehicle.getCapacity() <= 3000) {
                vehicle.setImpostRate(10);
            } else if (vehicle.getCapacity() > 3000) {
                vehicle.setImpostRate(5);
            }
        } else if (vehicle.getEngineType().name().equals("diesel")) {
            vehicle.setImpostRate(10);
        } else if (vehicle.getEngineType().name().equals("electric")) {
            vehicle.setImpostRate(0);
        }
    }

    // Method for determination impost rate by engine type and capacity if vehicle used
    private void determinationImpostRateByEngineTypeAndCapacityVehicleUsed(Vehicle vehicle) {
        if (vehicle.getEngineType().name().equals("petrol") || vehicle.getEngineType().name().equals("diesel")) {
            vehicle.setImpostRate(10);
        } else if (vehicle.getEngineType().name().equals("electric")) {
            vehicle.setImpostRate(0);
        }
    }
}