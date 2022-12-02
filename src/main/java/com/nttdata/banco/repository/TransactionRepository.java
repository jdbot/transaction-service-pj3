package com.nttdata.banco.repository;

import com.nttdata.banco.model.Transaction;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

/**
 * Transaction Repository.
 */
@Repository
public interface TransactionRepository extends ReactiveMongoRepository<Transaction, String> {

    Flux<Transaction> findAllByIdAccount(String idAccount);

    Flux<Transaction> findAllByIdClient(String idClient);

    Flux<Transaction> findAllByIdCard(String idCard);

    Flux<Transaction> findAllByTypeAndIdAccount(String type, String idAccount);
}
