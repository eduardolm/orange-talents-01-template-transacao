package br.com.zup.transacoes.dto.response;

import br.com.zup.transacoes.model.CreditCard;
import br.com.zup.transacoes.model.Transaction;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TransactionDetailsDtoTest {
    @Test
    public void testConstructor() {
        Transaction transaction = new Transaction();

        new TransactionDetailsDto(transaction);

        assertNull(transaction.getCreatedAt());
        assertNull(transaction.getId());
        assertNull(transaction.getStore());
        assertNull(transaction.getCreditCard());
        assertNull(transaction.getAmount());
        assertEquals("Transaction{id=null, amount=null, store=null, creditCard=null, createdAt=null}",
                transaction.toString());
    }

    @Test
    public void testConstructor2() {
        Transaction transaction = new Transaction();
        CreditCard creditCard = new CreditCard();
        transaction.setCreditCard(creditCard);

        new TransactionDetailsDto(transaction);

        assertNull(transaction.getCreatedAt());
        assertNull(transaction.getId());
        assertNull(transaction.getStore());
        assertSame(creditCard, transaction.getCreditCard());
        assertNull(transaction.getAmount());
        assertEquals("Transaction{id=null, amount=null, store=null, creditCard=CreditCard{id=null, email=null},"
                + " createdAt=null}", transaction.toString());
    }
}

