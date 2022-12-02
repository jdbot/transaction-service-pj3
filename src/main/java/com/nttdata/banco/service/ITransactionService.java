package com.nttdata.banco.service;

import com.nttdata.banco.model.Transaction;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Map;

/**
 * Transaction service interface.
 */
public interface ITransactionService {


    public Flux<Transaction> findAll();

    public Mono<Transaction> register(Transaction transaction);

    public Mono<Transaction> update(Transaction transaction);

    public Mono<Void> delete(String id);

    public Mono<Transaction> findById(String id);

    public Flux<Transaction> findTransactionsByAccountId(String idAccount);

    public Mono<Map<String, Double>> makeAmountAvgReport(String idClient);

    public Flux<Transaction> findTransactionsByCardId(String idCard);

    Flux<Transaction> findCommissionByAccountId(String idAccount, String startDate, String endDate);
}
