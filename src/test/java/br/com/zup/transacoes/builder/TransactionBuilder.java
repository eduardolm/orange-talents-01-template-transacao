package br.com.zup.transacoes.builder;

import br.com.zup.transacoes.model.CreditCard;
import br.com.zup.transacoes.model.Store;
import br.com.zup.transacoes.model.Transaction;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

public class TransactionBuilder {

    private UUID id = UUID.randomUUID();
    private BigDecimal amount;
    private Store store;
    private CreditCard creditCard;
    private LocalDateTime createdAt;


    public TransactionBuilder withAmount(BigDecimal amount) {
        this.amount = amount;
        return this;
    }

    public TransactionBuilder withStore(Store store) {
        this.store = store;
        return this;
    }

    public TransactionBuilder withCreditCard(CreditCard creditCard) {
        this.creditCard = creditCard;
        return this;
    }

    public TransactionBuilder withCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
        return this;
    }

    public Transaction build() {
        return new Transaction(id, amount, store, creditCard, createdAt);
    }
}
