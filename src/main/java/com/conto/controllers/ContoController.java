package com.conto.controllers;


import com.conto.Repository.Entity.Transaction;
import com.conto.pojo.*;
import com.conto.services.ClientFabrickServiceImpl;

import com.conto.services.TransactionService;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@Slf4j
@RestController
@RequestMapping("api")
public class ContoController {

    @Autowired
    ClientFabrickServiceImpl clientFabrickService;

    @Autowired
    TransactionService transactionService;

    @GetMapping("/lettura_saldo/{accountId}")
    public String getAccountBalance(@PathVariable String accountId) {

        String saldoResponse;
        log.info("accountId: " + accountId);
        try {
            saldoResponse = clientFabrickService.letturaSaldo(accountId);
        } catch (Exception e) {
            log.error("ERROR MANDARE MAIL @@@@@" + e.getMessage());
            saldoResponse = e.getMessage();
            e.printStackTrace();
        }
        log.info("saldoResponse: " + saldoResponse);
        return saldoResponse;
    }


    @GetMapping("/operazione_bonifico/{accountId}")
    public String operazioneBonifico(@PathVariable String accountId) {

        String operazioneBonifico = null;

        try {
            operazioneBonifico = clientFabrickService.operazioneBonifico(getBonificorequest(), accountId);
        } catch (Exception e) {
            log.error("ERROR MANDARE MAIL @@@@@" + e.getMessage());
            operazioneBonifico = e.getMessage();

            e.printStackTrace();
        }
        return operazioneBonifico;
    }

    @GetMapping("/operazione_transazioni/{accountId}")
    public String operazioneTransazioni(@PathVariable String accountId) {

        String operazioneTransazioni = null;
        log.info("accountId: " + accountId);
        try {
            operazioneTransazioni = clientFabrickService.operazioneTransazioni("2019-01-01", "2019-12-01");
        } catch (Exception e) {
            log.error("ERROR MANDARE MAIL @@@@@" + e.getMessage());
            e.printStackTrace();
        }
        log.info("operazioneTransazioni: " + operazioneTransazioni);

        JSONObject jsonObject = new JSONObject(operazioneTransazioni);
        JSONArray list = jsonObject.getJSONObject("payload").getJSONArray("list");
        for (int i = 0; i < list.length(); i++) {

            JSONObject transazione_json = (JSONObject) list.get(i);
            Transaction transaction = getTransaction(transazione_json);
            transactionService.save(transaction);

        }
        return "OK scritto su DB";
    }



    public Transaction getTransaction(JSONObject transazione_json) {

        log.info("transactionId " + transazione_json.getString("transactionId"));

        String transactionId = transazione_json.getString("transactionId");
        String operationId = transazione_json.getString("operationId");
        String accountingDate = transazione_json.getString("accountingDate");
        String valueDate = transazione_json.getString("valueDate");
        int amount = transazione_json.getInt("amount");
        String currency = transazione_json.getString("currency");
        String description = transazione_json.getString("description");

        Transaction transaction = new Transaction();
        transaction.setTransactionId(transactionId);
        transaction.setOperationId(operationId);
        transaction.setAccountingDate(accountingDate);
        transaction.setValueDate(valueDate);
        transaction.setAmount(amount);
        transaction.setCurrency(currency);
        transaction.setDescription(description);
        return transaction;
    }


    public BonificoRequest getBonificorequest() {

        BonificoRequestAccount bonificoRequestAccount = new BonificoRequestAccount();
        bonificoRequestAccount.setAccountCode("IT23A0336844430152923804660");
        bonificoRequestAccount.setBicCode("SELBIT2BXXX");

        BonificoRequestCreditor bonificoRequestCreditor = new BonificoRequestCreditor();
        bonificoRequestCreditor.setName("John Doe");
        bonificoRequestCreditor.setAccount(bonificoRequestAccount);

        BonificoRequest bonificoRequest = new BonificoRequest();
        bonificoRequest.setReceiverName("John Doe");
        bonificoRequest.setDescription("Payment invoice 75/2017");
        bonificoRequest.setCurrency("EUR");
        bonificoRequest.setAmount(20);
        bonificoRequest.setExecutionDate("2019-04-01");
        bonificoRequest.setFeeAccountId("45685475");
        bonificoRequest.setBonitficoId(20);
        bonificoRequest.setCreditor(bonificoRequestCreditor);

        return bonificoRequest;
    }


}
