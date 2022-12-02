package com.nttdata.banco.controller;

import com.nttdata.banco.model.Transaction;
import com.nttdata.banco.service.ITransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.GroupedFlux;
import reactor.core.publisher.Mono;

import javax.validation.constraints.NotNull;
import java.util.Map;

/**
 * Controller of Transaction.
 */
@RestController
@RequestMapping("/transaction")
public class TransactionController {

    @Autowired
    private ITransactionService transactionService;

    //Method to get all the transactions
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public Flux<Transaction> findAll() {
        return transactionService.findAll();
    }

    //Method to insert a new transaction
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public Mono<Transaction> register(@RequestBody Transaction transaction) {
        return  transactionService.register(transaction);
    }

    //Method to update a transaction
    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public Mono<Transaction> modify(@RequestBody Transaction transaction) {
        return  transactionService.update(transaction);
    }

    //Method to get a transaction by ID
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Mono<Transaction> findById(@PathVariable("id") String id) {
        return transactionService.findById(id);
    }

    //Method to delete a transaction
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Mono<Void> delete(@PathVariable("id") String id) {
        return transactionService.delete(id);
    }

    //Method to get all the transactions of an account
    @GetMapping("/account/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Flux<Transaction> findTransactionsByAccountId(@PathVariable("id") String accountId) {
        return transactionService.findTransactionsByAccountId(accountId);
    }

    @GetMapping("/client/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Mono<Map<String, Double>> makeAmountAvgReport(@PathVariable("id") String clientId) {
        return transactionService.makeAmountAvgReport(clientId);
    }

    //Method to get top 10 all the transactions of a card
    @GetMapping("/card/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Flux<Transaction> findTransactionsByCardId(@PathVariable("id") String cardId) {
        return transactionService.findTransactionsByCardId(cardId);
    }

    //Method to get all the commission of an account by date range
    @GetMapping("/commissionByAccount/{id}/{startDate}/{endDate}")
    @ResponseStatus(HttpStatus.OK)
    public Flux<Transaction> findCommissionByAccountId(@PathVariable("id") @NotNull String accountId,
                                                       @PathVariable("startDate") @NotNull String startDate,
                                                       @PathVariable("endDate") @NotNull String endDate) {
        return transactionService.findCommissionByAccountId(accountId,startDate,endDate);
    }
}
