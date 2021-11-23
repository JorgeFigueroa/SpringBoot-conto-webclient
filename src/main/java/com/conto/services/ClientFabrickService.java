package com.conto.services;

public interface ClientFabrickService {
    public String letturaSaldo(String accountId) throws Exception;

    public String operazioneBonifico(Object Object, String accountId) throws Exception;

    public String operazioneTransazioni(String fromAccountingDate, String toAccountingDate) throws Exception;
}
