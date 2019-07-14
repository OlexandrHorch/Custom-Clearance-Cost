package com.customclearancecost.service;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

public class ServiceForConnection {
    public HttpURLConnection makeConnection(HttpURLConnection connection, String query, String requestMethod) throws IOException {
        connection = (HttpURLConnection) new URL(query).openConnection();

        connection.setRequestMethod(requestMethod);
        connection.setUseCaches(false);
        connection.setConnectTimeout(5000);
        connection.setReadTimeout(5000);

        connection.connect();

        return connection;
    }


    public void disconnectConnection(HttpURLConnection connection) {
        if (connection != null) {
            connection.disconnect();
        }
    }
}