package com.customclearancecost.controller;

import com.customclearancecost.entity.*;
import com.customclearancecost.service.ServiceForCurrencyExchangeRate;
import com.customclearancecost.service.ServiceForDeterminationImpostRate;
import com.customclearancecost.service.ServiceForNumber;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.Calendar;

@Controller
public class HomeController {
    private ServiceForNumber serviceForNumber = new ServiceForNumber();
    private ServiceForDeterminationImpostRate serviceForDeterminationImpostRate = new ServiceForDeterminationImpostRate();
    private ServiceForCurrencyExchangeRate serviceForCurrencyExchangeRate = new ServiceForCurrencyExchangeRate();

    @GetMapping("/")
    public ModelAndView index(@RequestParam(required = false) Owner ownerRequest,
                              @RequestParam(required = false) Age ageRequest,
                              @RequestParam(required = false) YearOfManufacture yearOfManufactureRequest,
                              @RequestParam(required = false) EngineType engineTypeRequest,
                              @RequestParam(required = false) Integer capacityRequest,
                              @RequestParam(required = false) CountryOfOrigin countryOfOriginRequest,
                              @RequestParam(required = false) CurrencyOfContract currencyOfContractRequest,
                              @RequestParam(required = false) Float priceInCurrencyRequest) {
        ModelAndView result = new ModelAndView("home");
        Vehicle vehicle = new Vehicle();

        // filling the object with input values
        if (ownerRequest != null) {
            vehicle.setOwner(ownerRequest);
        }
        if (ageRequest != null) {
            vehicle.setAge(ageRequest);
        }
        if (yearOfManufactureRequest != null) {
            vehicle.setYearOfManufacture(yearOfManufactureRequest);
        }
        if (engineTypeRequest != null) {
            vehicle.setEngineType(engineTypeRequest);
        }
        if (capacityRequest != null) {
            vehicle.setCapacity(capacityRequest);
        }
        if (countryOfOriginRequest != null) {
            vehicle.setCountryOfOrigin(countryOfOriginRequest);
        }
        if (currencyOfContractRequest != null) {
            vehicle.setCurrencyOfContract(currencyOfContractRequest);
        }
        if (priceInCurrencyRequest != null) {
            vehicle.setPriceInCurrency(priceInCurrencyRequest);
        }

        calculationAndAddAgeCoefficient(vehicle);

        // checking data for calculation taxes
        if (vehicle.getCapacity() != null && vehicle.getPriceInCurrency() != null) {
            result.addObject("vehicle", calculationTaxes(vehicle));
        }

        result.addObject("vehicle", vehicle);

        return result;
    }


    // Method for calculation and add age coefficient
    // Change data at the beginning of the year!!!
    private void calculationAndAddAgeCoefficient(Vehicle vehicle) {
        Calendar calendar = Calendar.getInstance();
        int nowYear = calendar.get(Calendar.YEAR);
        int yearOfManufacture;
        int differenceOfYears;

        if (vehicle.getYearOfManufacture() != null) {
            // Get year of manufacture
            if (vehicle.getYearOfManufacture().getDescription().equals("2003 і раніше")) { // Change data at the beginning of the year!!!
                yearOfManufacture = 2003; // Change data at the beginning of the year!!!
            } else {
                yearOfManufacture = Integer.parseInt(vehicle.getYearOfManufacture().getDescription());
            }

            differenceOfYears = nowYear - yearOfManufacture;

            if (differenceOfYears <= 2) {
                vehicle.setAgeCoefficient(1);
            } else {
                vehicle.setAgeCoefficient(differenceOfYears - 1);
            }
        }
    }


    // Method for determination impost rate
    private void determinationImpostRate(Vehicle vehicle) {
        serviceForDeterminationImpostRate.determinationImpostRateByOwner(vehicle);
    }


    // Method for determination excise rate
    private void determinationExciseRate(Vehicle vehicle) {
        if (vehicle.getEngineType().name().equals("petrol") && vehicle.getCapacity() <= 3000) {
            vehicle.setExciseRate(serviceForNumber.roundingNumber(
                    (double) 50 / 1000 * vehicle.getAgeCoefficient(),
                    2));

        } else if (vehicle.getEngineType().name().equals("petrol") && vehicle.getCapacity() > 3000) {
            vehicle.setExciseRate(serviceForNumber.roundingNumber(
                    (double) 100 / 1000 * vehicle.getAgeCoefficient(),
                    2));

        } else if (vehicle.getEngineType().name().equals("diesel") && vehicle.getCapacity() <= 3500) {
            vehicle.setExciseRate(serviceForNumber.roundingNumber(
                    (double) 75 / 1000 * vehicle.getAgeCoefficient(),
                    2));

        } else if (vehicle.getEngineType().name().equals("diesel") && vehicle.getCapacity() > 3500) {
            vehicle.setExciseRate(serviceForNumber.roundingNumber(
                    (double) 150 / 1000 * vehicle.getAgeCoefficient(),
                    2));

        } else if (vehicle.getEngineType().name().equals("electric")) {
            vehicle.setExciseRate(1);
        }
    }


    // Method for determination VAT rate
    private void determinationVATRate(Vehicle vehicle) {
        if (vehicle.getEngineType().name().equals("electric")) {
            vehicle.setVATRate(0);
        } else {
            vehicle.setVATRate(20);
        }
    }


    // Method for calculation taxes
    private Vehicle calculationTaxes(Vehicle vehicle) {
        double exchangeRateCurrencyOfContract = serviceForCurrencyExchangeRate.getCurrencyExchangeRateNBU(vehicle.getCurrencyOfContract().name());
        double exchangeRateEUR = serviceForCurrencyExchangeRate.getCurrencyExchangeRateNBU("EUR");

        // Calculation Impost
        vehicle.setImpostBasis(serviceForNumber.roundingNumber(
                vehicle.getPriceInCurrency() * exchangeRateCurrencyOfContract,
                2));
        determinationImpostRate(vehicle);
        vehicle.setImpost(serviceForNumber.roundingNumber(
                vehicle.getImpostBasis() * vehicle.getImpostRate() / 100,
                2));

        // Calculation Excise
        vehicle.setExciseBasis(vehicle.getCapacity());
        determinationExciseRate(vehicle);
        vehicle.setExcise(serviceForNumber.roundingNumber(
                vehicle.getExciseBasis() * vehicle.getExciseRate() * exchangeRateEUR,
                2));

        // Calculation VAT
        vehicle.setVATBasis(serviceForNumber.roundingNumber(
                vehicle.getImpostBasis() + vehicle.getImpost() + vehicle.getExcise(),
                2));
        determinationVATRate(vehicle);
        vehicle.setVAT(serviceForNumber.roundingNumber(
                vehicle.getVATBasis() * vehicle.getVATRate() / 100,
                2));

        return vehicle;
    }
}