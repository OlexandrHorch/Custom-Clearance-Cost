package com.customclearancecost.requests;

import com.alibaba.fastjson.JSON;
import com.customclearancecost.entity.Currency;
import com.customclearancecost.service.ServiceForConnection;

import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;

public class CurrencyRequests extends Component {
    private ServiceForConnection serviceForConnection = new ServiceForConnection();
    private HttpURLConnection connection;
    private Currency currency = null;

    public Double getExchangeRateNBUByCurrencyAndData(String currencyCode, String date) {
        String query = "https://bank.gov.ua/NBUStatService/v1/statdirectory/exchange?valcode=" + currencyCode + "&date=" + date + "&json";

        try {
            connection = serviceForConnection.makeConnection(null, query, "GET");
            currency = readResponse(connection);
        } catch (Throwable cause) {
            cause.printStackTrace();
        } finally {
            serviceForConnection.disconnectConnection(connection);
        }
        return currency.getRate();
    }


    private Currency readResponse(HttpURLConnection connection) throws IOException {
        String line;

        if (HttpURLConnection.HTTP_OK == connection.getResponseCode()) {
            BufferedReader in = new BufferedReader(
                    new InputStreamReader(connection.getInputStream()));

            // read input stream ("in")
            String linePlus = "";
            while (( line = in.readLine() ) != null) {
                linePlus = linePlus + line;
            }

            // Removal '[' and ']'
            String replacedLinePlus;
            replacedLinePlus = linePlus.replace('[', ' ');
            replacedLinePlus = replacedLinePlus.replace(']', ' ');

            // transform Json to object
            currency = (Currency) parseFromJson(replacedLinePlus);

        } else {
            // TODO: if request code not 200
            System.out.println("Інформація про курс валюти не знайдена.");
        }
        return currency;
    }


    private static Object parseFromJson(String json) {
        Object object = JSON.parseObject(json, Currency.class);
        return object;
    }
}