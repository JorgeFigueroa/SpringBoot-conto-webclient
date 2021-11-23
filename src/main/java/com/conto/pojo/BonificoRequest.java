package com.conto.pojo;


import java.io.Serializable;


public class BonificoRequest implements Serializable {

    private Integer bonitficoId;
    private String receiverName;
    private String description;
    private String currency;
    private Integer amount;
    private String executionDate;
    private String feeAccountId;
    private BonificoRequestCreditor creditor;

    public Integer getBonitficoId() {
        return bonitficoId;
    }

    public void setBonitficoId(Integer bonitficoId) {
        this.bonitficoId = bonitficoId;
    }

    public String getReceiverName() {
        return receiverName;
    }

    public void setReceiverName(String receiverName) {
        this.receiverName = receiverName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public String getExecutionDate() {
        return executionDate;
    }

    public void setExecutionDate(String executionDate) {
        this.executionDate = executionDate;
    }

    public String getFeeAccountId() {
        return feeAccountId;
    }

    public void setFeeAccountId(String feeAccountId) {
        this.feeAccountId = feeAccountId;
    }

    public BonificoRequestCreditor getCreditor() {
        return creditor;
    }

    public void setCreditor(BonificoRequestCreditor creditor) {
        this.creditor = creditor;
    }


}
