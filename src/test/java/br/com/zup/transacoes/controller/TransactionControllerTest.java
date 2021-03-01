package br.com.zup.transacoes.controller;

import br.com.zup.transacoes.builder.CreditCardBuilder;
import br.com.zup.transacoes.model.CreditCard;
import br.com.zup.transacoes.model.Store;
import br.com.zup.transacoes.model.Transaction;
import br.com.zup.transacoes.repository.CreditCardRepository;
import br.com.zup.transacoes.repository.TransactionRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.Optional;

import static org.mockito.Mockito.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class TransactionControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TransactionController controller;

    @MockBean
    private CreditCardRepository creditCardRepository;

    @MockBean
    private TransactionRepository transactionRepository;

    private CreditCard creditCard;

    @BeforeEach
    public void setup() {
        CreditCard creditCard = new CreditCardBuilder()
                .withId("b0012b90-42c8-40e6-903b-64acb3aa649b")
                .withEmail("test@email.com")
                .build();

        this.creditCard = creditCard;

        when(creditCardRepository.findById("b0012b90-42c8-40e6-903b-64acb3aa649b"))
                .thenReturn(Optional.ofNullable(creditCard));
    }

    @Test
    public void testFindTop10ByCreditCard_IdOrderByCreatedAtDesc() throws Exception {
        when(this.transactionRepository.findTop10ByCreditCard_IdOrderByCreatedAtDesc(anyString()))
                .thenReturn(new ArrayList<>());

        Transaction transaction = new Transaction();
        transaction.setCreditCard(new CreditCard());
        transaction.setId("42");
        transaction.setStore(new Store());

        CreditCard creditCard = new CreditCard();
        creditCard.addTransaction(transaction);

        Store store = new Store();
        store.setId(123L);

        Transaction transaction1 = new Transaction();
        transaction1.setCreditCard(creditCard);
        transaction1.setId("42");
        transaction1.setStore(store);

        CreditCard creditCard1 = new CreditCard();
        creditCard1.addTransaction(transaction1);
        Optional<CreditCard> ofResult = Optional.of(creditCard1);
        when(this.creditCardRepository.findById(anyString())).thenReturn(ofResult);

        mockMvc.perform(get("/api/transactions")
                .param("creditCardId", "foo")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void testFindTop10ByCreditCard_IdOrderByCreatedAtDescWhenIdNotFound() throws Exception {
        when(this.transactionRepository.findTop10ByCreditCard_IdOrderByCreatedAtDesc(anyString()))
                .thenReturn(new ArrayList<>());

        when(this.creditCardRepository.findById(anyString())).thenReturn(Optional.empty());

        mockMvc.perform(get("/api/transactions")
                .param("creditCardId", "foo")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }
}

