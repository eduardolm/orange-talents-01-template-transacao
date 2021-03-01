package br.com.zup.transacoes.dto.response;

import br.com.zup.transacoes.builder.CreditCardBuilder;
import br.com.zup.transacoes.builder.StoreMessageResponseDtoBuilder;
import br.com.zup.transacoes.model.CreditCard;
import br.com.zup.transacoes.model.Transaction;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.test.context.ActiveProfiles;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

@ActiveProfiles("test")
public class TransactionMessageDtoTest {

    private CreditCard creditCard;
    private StoreMessageResponseDto store;

    @BeforeEach
    public void setup() {

        this.creditCard = new CreditCardBuilder()
                .withId("c63fd0e0-eccb-4af3-9d49-39cde0ffdaf1")
                .withEmail("test@email.com")
                .build();

        StoreMessageResponseDto store = new StoreMessageResponseDtoBuilder()
                .withName("Levi's")
                .withCity("Orlando")
                .withAddress("18327 Mills Groves, West Marquita, SD 31244")
                .build();

        this.store = store;
    }

    @Test
    public void shouldCreateNewTransactionMessageInstanceWithOverloadedConstructor() {
        TransactionMessageDto transactionMessageDto = new TransactionMessageDto(
                "c63fd0e0-eccb-4af3-9d49-39cde0ffdaf1", BigDecimal.TEN, store, creditCard,
                LocalDateTime.of(2021, 1, 12, 13, 25, 0));

        assertEquals("c63fd0e0-eccb-4af3-9d49-39cde0ffdaf1", transactionMessageDto.getId().toString());
        assertEquals(BigDecimal.TEN, transactionMessageDto.getValor());
        assertEquals("Levi's", transactionMessageDto.getEstabelecimento().getNome());
        assertEquals("test@email.com", transactionMessageDto.getCartao().getEmail());
        assertEquals(LocalDateTime.of(2021, 1, 12, 13, 25, 0),
                transactionMessageDto.getEfetivadaEm());
    }

    @Test
    public void testToModel() {
        TransactionMessageDto transactionMessageDto = new TransactionMessageDto(
                "c63fd0e0-eccb-4af3-9d49-39cde0ffdaf1", BigDecimal.TEN, store, creditCard,
                LocalDateTime.of(2021, 1, 12, 13, 25, 0));

        Transaction transaction = transactionMessageDto.toModel();

        assertEquals("c63fd0e0-eccb-4af3-9d49-39cde0ffdaf1", transaction.getId().toString());
        assertEquals(BigDecimal.TEN, transaction.getAmount());
        assertEquals("Levi's", transaction.getStore().getName());
        assertEquals("test@email.com", transaction.getCreditCard().getEmail());
        assertEquals(LocalDateTime.of(2021, 1, 12, 13, 25, 0),
                transaction.getCreatedAt());
    }

    @Test
    public void testToString() {
        TransactionMessageDto transactionMessageDto = new TransactionMessageDto(
                "c63fd0e0-eccb-4af3-9d49-39cde0ffdaf1", BigDecimal.TEN, store, creditCard,
                LocalDateTime.of(2021, 1, 12, 13, 25, 0));

        assertEquals("TransactionMessageDto{id=c63fd0e0-eccb-4af3-9d49-39cde0ffdaf1, valor=10, " +
                "estabelecimento=StoreMessageResponseDto{name='Levi's', cidade='Orlando', endereco='18327 Mills " +
                "Groves, West Marquita, SD 31244'}, cartao=CreditCard{id=c63fd0e0-eccb-4af3-9d49-39cde0ffdaf1, " +
                "email=test@email.com}, efetivadaEm=2021-01-12T13:25}", transactionMessageDto.toString());
    }

    @Test
    public void testEquals() {
        TransactionMessageDto transactionMessageDto1 = new TransactionMessageDto(
                "c63fd0e0-eccb-4af3-9d49-39cde0ffdaf1", BigDecimal.TEN, store, creditCard,
                LocalDateTime.of(2021, 1, 12, 13, 25, 0));

        TransactionMessageDto transactionMessageDto2 = new TransactionMessageDto(
                "c63fd0e0-eccb-4af3-9d49-39cde0ffdaf1", BigDecimal.TEN, store, creditCard,
                LocalDateTime.of(2021, 1, 12, 13, 25, 0));

        TransactionMessageDto transactionMessageDto3 = new TransactionMessageDto();
        TransactionMessageDto transactionMessageDto4 = new TransactionMessageDto();

        assertEquals(transactionMessageDto1, transactionMessageDto2);
        assertEquals(transactionMessageDto3, transactionMessageDto4);
        assertNotEquals(transactionMessageDto1, transactionMessageDto3);
        assertNotEquals(transactionMessageDto2, transactionMessageDto4);
    }

    @Test
    public void testHashCode() {
        TransactionMessageDto transactionMessageDto1 = new TransactionMessageDto(
                "c63fd0e0-eccb-4af3-9d49-39cde0ffdaf1", BigDecimal.TEN, store, creditCard,
                LocalDateTime.of(2021, 1, 12, 13, 25, 0));

        TransactionMessageDto transactionMessageDto2 = new TransactionMessageDto(
                "c63fd0e0-eccb-4af3-9d49-39cde0ffdaf1", BigDecimal.TEN, store, creditCard,
                LocalDateTime.of(2021, 1, 12, 13, 25, 0));

        TransactionMessageDto transactionMessageDto3 = new TransactionMessageDto();
        TransactionMessageDto transactionMessageDto4 = new TransactionMessageDto();

        assertEquals(transactionMessageDto1.hashCode(), transactionMessageDto2.hashCode());
        assertEquals(transactionMessageDto3.hashCode(), transactionMessageDto4.hashCode());
        assertNotEquals(transactionMessageDto1.hashCode(), transactionMessageDto3.hashCode());
        assertNotEquals(transactionMessageDto2.hashCode(), transactionMessageDto4.hashCode());
    }
}
