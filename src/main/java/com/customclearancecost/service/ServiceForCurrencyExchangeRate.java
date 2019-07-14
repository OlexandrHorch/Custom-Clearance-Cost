package com.customclearancecost.service;

import com.customclearancecost.requests.CurrencyRequests;

import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;
import java.util.TimeZone;

public class ServiceForCurrencyExchangeRate {
    private CurrencyRequests currencyRequests = new CurrencyRequests();

    public double getCurrencyExchangeRateNBU(String currencyName) {
        String currencyCode = null;

        if ("US_DOLLAR".equals(currencyName)) {
            currencyCode = "USD";
        } else if ("EUR".equals(currencyName)) {
            currencyCode = "EUR";
        } else if ("POUNDS_STERLING".equals(currencyName)) {
            currencyCode = "GBP";
        } else if ("CANADIAN_DOLLAR".equals(currencyName)) {
            currencyCode = "CAD";
        } else if ("ZLOTY".equals(currencyName)) {
            currencyCode = "PLN";
        } else if ("SSWISS_FRANC".equals(currencyName)) {
            currencyCode = "CHF";
        } else if ("СHESKA_KRONE".equals(currencyName)) {
            currencyCode = "CZK";
        }

        String date = getCurrentDate();

        return currencyRequests.getExchangeRateNBUByCurrencyAndData(currencyCode, date);
    }


    private String getCurrentDate() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd");
        GregorianCalendar calendar = new GregorianCalendar(TimeZone.getTimeZone("US/Central"));
        return simpleDateFormat.format(calendar.getTime());
    }
}