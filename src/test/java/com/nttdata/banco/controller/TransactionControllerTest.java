package com.nttdata.banco.controller;

import com.nttdata.banco.model.Transaction;
import com.nttdata.banco.repository.TransactionRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class TransactionControllerTest {

    @Mock
    TransactionRepository transactionRepositoryMock;

    @Autowired
    TransactionController transactionController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        Transaction transaction = new Transaction("1","20-11-2022",100,"deposit","ClientID", "AccountID", 100, "CardID" );
        Mono<Transaction> transactionMock = Mono.just(transaction);
        Mockito.when(transactionRepositoryMock.findById(ArgumentMatchers.anyString()).thenReturn(transactionMock));
        List<Transaction> transactions = new ArrayList<>();
        transactions.add(transaction);
        transactions.add(new Transaction("2","23-11-2022",20,"withdrawl","ClientID", "AccountID", 80, "CardID" ));
        transactions.add(new Transaction("3","25-11-2022",100,"deposit","ClientID", "AccountID", 180, "CardID" ));
        transactions.add(new Transaction("4","29-11-2022",150,"deposit","ClientID", "AccountID", 30, "CardID" ));
        Flux<Transaction> fluxTransactionsMock = Flux.fromIterable(transactions);
        Mockito.when(transactionRepositoryMock.findAll()).thenReturn(fluxTransactionsMock);

    }

    @Test
    void findAll() {
        Flux<Transaction> transactionResponse;
        transactionResponse = transactionController.findAll();
    }

    @Test
    void register() {
    }

    @Test
    void modify() {
    }

    @Test
    void findById() {
        Mono<Transaction> transactionResponse;
        transactionResponse = transactionController.findById("1");
        Assertions.assertEquals(100,transactionResponse.map(Transaction::getAmount));
    }

    @Test
    void delete() {
    }

    @Test
    void findTransactionsByAccountId() {
    }

    @Test
    void makeAmountAvgReport() {
    }

    @Test
    void findTransactionsByCardId() {
    }

    @Test
    void findCommissionByAccountId() {
    }

    @AfterEach
    void tearDown() {
    }
}