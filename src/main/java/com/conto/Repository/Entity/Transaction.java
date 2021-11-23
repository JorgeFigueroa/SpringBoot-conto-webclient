package com.conto.Repository.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="transactions")
public class Transaction {
    @Id
    @GeneratedValue
    private int id;
    private String transactionId;
    private String operationId;
    private String accountingDate;
    private String valueDate;
    private Integer amount;
    private String currency;
    private String description;


}
