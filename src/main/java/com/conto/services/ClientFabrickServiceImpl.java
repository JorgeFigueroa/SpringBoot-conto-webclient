package com.conto.services;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class ClientFabrickServiceImpl implements ClientFabrickService {


    private GsonBuilder builder = new GsonBuilder();
    private Gson gson = builder.create();

    @Autowired
    Environment environment;


    public String getUrl() {
        return environment.getProperty("rest.url");
    }

    private String getAuth_schema() {
        return environment.getProperty("rest.auth_schema");
    }

    private String getApi_key() {
        return environment.getProperty("rest.api_key");
    }


    public String letturaSaldo(String accountId) throws Exception {

        String URL = getUrl() + "/api/gbs/banking/v4.0/accounts/" + accountId + "/balance";
        log.info("REQUEST GET URL: " + URL);
        Unirest.setTimeouts(0, 0); // NO TIMEOUT
        HttpResponse<String> httpResponse = Unirest.get(URL)
                .header("Auth-Schema", getAuth_schema())
                .header("Api-Key", getApi_key())
                .asString();

        log.info("RESPONSE STATUS: " + httpResponse.getStatus());
        if (httpResponse.getStatus() != 200) {
            throw new Exception(httpResponse.getBody());
        }

        return httpResponse.getBody();


    }



    public String operazioneBonifico(Object Object, String accountId) throws Exception {
        String requestJson = gson.toJson(Object);
        String URL = getUrl() + "/api/gbs/banking/v4.0/accounts/" + accountId + "/payments/money-transfers";

        log.info("POST Request URL: " + URL);
        log.info("body: " + requestJson);
        HttpResponse<String> httpResponse = Unirest.post(URL)
                .header("Auth-Schema", getAuth_schema())
                .header("Api-Key", getApi_key())
                .body(requestJson)
                .asString();

        log.info("POST - status: " + httpResponse.getStatus());

        if (httpResponse.getStatus() != 201) {
            throw new Exception(httpResponse.getBody());
        }

        return httpResponse.getBody();
    }


    public String operazioneTransazioni(String fromAccountingDate, String toAccountingDate) throws Exception {

        String URL = getUrl() + "/api/gbs/banking/v4.0/accounts/14537780/transactions?fromAccountingDate=" + fromAccountingDate + "&toAccountingDate=" + toAccountingDate;
        log.info("REQUEST GET URL: " + URL);
        Unirest.setTimeouts(0, 0); // NO TIMEOUT
        HttpResponse<String> httpResponse = Unirest.get(URL)
                .header("Auth-Schema", getAuth_schema())
                .header("Api-Key", getApi_key())
                .asString();

        log.info("RESPONSE STATUS: " + httpResponse.getStatus());
        if (httpResponse.getStatus() != 200) {
            throw new Exception(httpResponse.getBody());
        }

        return httpResponse.getBody();


    }


}
