package br.com.zup.transacoes.model;

import br.com.zup.transacoes.builder.CreditCardBuilder;
import br.com.zup.transacoes.builder.StoreBuilder;
import br.com.zup.transacoes.builder.TransactionBuilder;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.test.context.ActiveProfiles;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

@ActiveProfiles("test")
public class TransactionTest {

    private CreditCard creditCard;
    private Store store;

    @BeforeEach
    public void setup() {

        this.creditCard = new CreditCardBuilder()
                .withId(UUID.fromString("c63fd0e0-eccb-4af3-9d49-39cde0ffdaf1"))
                .withEmail("test@email.com")
                .build();

        Store store = new StoreBuilder()
                .withName("Levi's")
                .withCity("Orlando")
                .withAddress("18327 Mills Groves, West Marquita, SD 31244")
                .build();

        store.setId(1L);
        this.store = store;
    }

    @Test
    public void shouldCreateNewTransactionInstanceWithOverloadedConstructor() {
        Transaction transaction = new TransactionBuilder()
                .withAmount(BigDecimal.TEN)
                .withStore(store)
                .withCreditCard(creditCard)
                .withCreatedAt(LocalDateTime.of(2021, 01, 12, 13, 25, 00))
                .build();
        transaction.setId(UUID.fromString("c63fd0e0-eccb-4af3-9d49-39cde0ffdaf1"));

        assertEquals("Levi's", transaction.getStore().getName());
        assertEquals("test@email.com", transaction.getCreditCard().getEmail());
        assertEquals("c63fd0e0-eccb-4af3-9d49-39cde0ffdaf1", transaction.getId().toString());
        assertEquals(BigDecimal.TEN, transaction.getAmount());
        assertEquals(LocalDateTime.of(2021, 01, 12, 13, 25, 00), transaction.getCreatedAt());
        assertEquals(creditCard, transaction.getCreditCard());
    }

    @Test
    public void testToString() {
        Transaction transaction = new TransactionBuilder()
                .withAmount(BigDecimal.TEN)
                .withStore(store)
                .withCreditCard(creditCard)
                .withCreatedAt(LocalDateTime.of(2021, 01, 12, 13, 25, 00))
                .build();
        transaction.setId(UUID.fromString("c63fd0e0-eccb-4af3-9d49-39cde0ffdaf1"));

        assertEquals("Transaction{id=c63fd0e0-eccb-4af3-9d49-39cde0ffdaf1, " +
                "amount=10, store=Store{id=1, name='Levi's', city='Orlando', address='18327 Mills Groves, West " +
                "Marquita, SD 31244'}, creditCard=CreditCard{id=c63fd0e0-eccb-4af3-9d49-39cde0ffdaf1, " +
                "email=test@email.com}, createdAt=2021-01-12T13:25}", transaction.toString());
    }

    @Test
    public void testEquals() {
        Transaction transaction1 = new Transaction();
        Transaction transaction2 = new Transaction();
        Transaction transaction3 = new TransactionBuilder()
                .withAmount(BigDecimal.TEN)
                .withStore(store)
                .withCreditCard(creditCard)
                .withCreatedAt(LocalDateTime.of(2021, 01, 12, 13, 25, 00))
                .build();
        transaction3.setId(UUID.fromString("c63fd0e0-eccb-4af3-9d49-39cde0ffdaf1"));

        Transaction transaction4 = new TransactionBuilder()
                .withAmount(BigDecimal.TEN)
                .withStore(store)
                .withCreditCard(creditCard)
                .withCreatedAt(LocalDateTime.of(2021, 01, 12, 13, 25, 00))
                .build();
        transaction4.setId(UUID.fromString("c63fd0e0-eccb-4af3-9d49-39cde0ffdaf1"));

        assertEquals(transaction1, transaction2);
        assertEquals(transaction3, transaction4);
        assertNotEquals(transaction1, transaction3);
        assertNotEquals(transaction2, transaction4);
    }

    @Test
    public void testHashCode() {
        Transaction transaction1 = new Transaction();
        Transaction transaction2 = new Transaction();
        Transaction transaction3 = new TransactionBuilder()
                .withAmount(BigDecimal.TEN)
                .withStore(store)
                .withCreditCard(creditCard)
                .withCreatedAt(LocalDateTime.of(2021, 01, 12, 13, 25, 00))
                .build();
        transaction3.setId(UUID.fromString("c63fd0e0-eccb-4af3-9d49-39cde0ffdaf1"));

        Transaction transaction4 = new TransactionBuilder()
                .withAmount(BigDecimal.TEN)
                .withStore(store)
                .withCreditCard(creditCard)
                .withCreatedAt(LocalDateTime.of(2021, 01, 12, 13, 25, 00))
                .build();
        transaction4.setId(UUID.fromString("c63fd0e0-eccb-4af3-9d49-39cde0ffdaf1"));

        assertEquals(transaction1.hashCode(), transaction2.hashCode());
        assertEquals(transaction3.hashCode(), transaction4.hashCode());
        assertNotEquals(transaction1.hashCode(), transaction3.hashCode());
    }
}
