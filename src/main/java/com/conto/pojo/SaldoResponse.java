package com.conto.pojo;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
public class SaldoResponse {
    private String status;
    private List<Object> error = null;
    private SaldoResponsePayload payload;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<Object> getError() {
        return error;
    }

    public void setError(List<Object> error) {
        this.error = error;
    }

    public SaldoResponsePayload getPayload() {
        return payload;
    }

    public void setPayload(SaldoResponsePayload payload) {
        this.payload = payload;
    }

    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}