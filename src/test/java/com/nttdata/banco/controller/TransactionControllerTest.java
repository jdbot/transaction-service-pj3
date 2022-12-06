package com.nttdata.banco.controller;

import com.nttdata.banco.model.Transaction;
import com.nttdata.banco.service.ITransactionService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Collections;

@AutoConfigureWebTestClient
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
class TransactionControllerTest {

    @Autowired
    private WebTestClient webTestClient;

    @Autowired
    private ITransactionService transactionService;

    @Test
    void register() {
        Transaction transaction = new Transaction("638e419c35246a5c346a2f21", "2022-12-05", 10, "credit payment" , "638a5d6e5968997b3866f062", "638e33333d125635f969545a", 10, "638e2a5282a74f71937756f3");
        webTestClient.post()
                .uri("/transaction")
                .body(Mono.just(transaction), Transaction.class)
                .exchange()
                .expectStatus().isOk()
                .expectBody(Transaction.class)
                .consumeWith( response ->{
                    Transaction tr = response.getResponseBody();
                    Assertions.assertThat(tr.getAmount() == 10).isTrue();
                });
    }

    @Test
    void findAll() {
        webTestClient.get()
                .uri("/transaction")
                .exchange()
                .expectStatus().isOk()
                .expectBodyList(Transaction.class)
                .consumeWith(response ->{
                    Flux<Transaction> transactions = Flux.fromIterable(response.getResponseBody());
                    Assertions.assertThat(transactions.hasElements());
                });
    }

    @Test
    void findById() {
        Transaction transaction = transactionService.findById("638e419c35246a5c346a2f21").block();
        webTestClient.get()
                .uri("/transaction/{id}", Collections.singletonMap("id", transaction.getId()))
                .exchange()
                .expectStatus().isOk()
                .expectBody(Transaction.class)
                .consumeWith( response ->{
                    Mono<Transaction> tr = Mono.just(response.getResponseBody());
                    Assertions.assertThat(transaction.getAmount() == 10).isTrue();
                });
    }

    @Test
    void modify() {
        Transaction transaction = transactionService.findById("638e419c35246a5c346a2f21").block();
        transaction.setTransactionDate("2022-12-06");
        webTestClient.put()
                .uri("/transaction")
                .body(Mono.just(transaction), Transaction.class)
                .exchange()
                .expectStatus().isOk()
                .expectBody(Transaction.class)
                .consumeWith( response ->{
                    Transaction tr = response.getResponseBody();
                    Assertions.assertThat(tr.getAmount() == 10).isTrue();
                    Assertions.assertThat(tr.getTransactionDate().equals("2022-12-06")).isTrue();
                });
    }

    @Test
    void delete() {
        Transaction transaction = transactionService.findById("638e419c35246a5c346a2f21").block();

        webTestClient.delete()
                .uri("/transaction/{id}", Collections.singletonMap("id",transaction.getId()))
                .exchange()
                .expectStatus().isOk()
                .expectBody();
    }
}