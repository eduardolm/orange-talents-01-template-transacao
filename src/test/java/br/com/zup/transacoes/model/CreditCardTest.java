package br.com.zup.transacoes.model;

import br.com.zup.transacoes.builder.CreditCardBuilder;
import br.com.zup.transacoes.builder.StoreBuilder;
import br.com.zup.transacoes.builder.TransactionBuilder;
import org.junit.jupiter.api.Test;
import org.springframework.test.context.ActiveProfiles;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@ActiveProfiles("test")
public class CreditCardTest {

    @Test
    public void shouldCreateCreditCardInstanceOverloadedConstructor() {
        CreditCard creditCard = new CreditCardBuilder()
                .withId("c63fd0e0-eccb-4af3-9d49-39cde0ffdaf1")
                .withEmail("test@email.com")
                .build();

        assertEquals("c63fd0e0-eccb-4af3-9d49-39cde0ffdaf1", creditCard.getId());
        assertEquals("test@email.com", creditCard.getEmail());
        assertEquals(0, creditCard.getTransactions().size());
    }

    @Test
    public void testToString() {
        CreditCard creditCard = new CreditCardBuilder()
                .withId("c63fd0e0-eccb-4af3-9d49-39cde0ffdaf1")
                .withEmail("test@email.com")
                .build();

        assertEquals("CreditCard{id=c63fd0e0-eccb-4af3-9d49-39cde0ffdaf1, email=test@email.com}", creditCard.toString());
    }

    @Test
    public void testEquals() {
        CreditCard creditCard1 = new CreditCard();
        CreditCard creditCard2 = new CreditCard();

        assertEquals(creditCard2, creditCard1);
    }

    @Test
    public void testEquals2() {
        CreditCard creditCard1 = new CreditCardBuilder()
                .withId("c63fd0e0-eccb-4af3-9d49-39cde0ffdaf1")
                .withEmail("test@email.com")
                .build();

        CreditCard creditCard2 = new CreditCardBuilder()
                .withId("c63fd0e0-eccb-4af3-9d49-39cde0ffdaf1")
                .withEmail("test@email.com")
                .build();

        assertEquals(creditCard1, creditCard2);
    }

    @Test
    public void testHashCode() {
        CreditCard creditCard1 = new CreditCardBuilder()
                .withId("c63fd0e0-eccb-4af3-9d49-39cde0ffdaf1")
                .withEmail("test@email.com")
                .build();

        CreditCard creditCard2 = new CreditCardBuilder()
                .withId("c63fd0e0-eccb-4af3-9d49-39cde0ffdaf1")
                .withEmail("test@email.com")
                .build();

        CreditCard creditCard3 = new CreditCard();
        CreditCard creditCard4 = new CreditCard();

        assertEquals(creditCard1.hashCode(), creditCard2.hashCode());
        assertEquals(creditCard3.hashCode(), creditCard4.hashCode());
        assertNotEquals(creditCard1, creditCard3);
        assertNotEquals(creditCard2, creditCard4);
    }

    @Test
    public void testAddTransaction() {
        CreditCard creditCard = new CreditCardBuilder()
                .withId("c63fd0e0-eccb-4af3-9d49-39cde0ffdaf1")
                .withEmail("test@email.com")
                .build();

        Store store = new StoreBuilder()
                .withName("Levi's")
                .withCity("Orlando")
                .withAddress("18327 Mills Groves, West Marquita, SD 31244")
                .build();

        store.setId(1L);

        Transaction transaction = new TransactionBuilder()
                .withAmount(BigDecimal.TEN)
                .withStore(store)
                .withCreditCard(creditCard)
                .withCreatedAt(LocalDateTime.now())
                .build();

        creditCard.addTransaction(transaction);

        assertEquals(BigDecimal.TEN, creditCard.getTransactions().get(0).getAmount());
        assertEquals("Levi's", creditCard.getTransactions().get(0).getStore().getName());
        assertEquals("Orlando", creditCard.getTransactions().get(0).getStore().getCity());
    }
}
