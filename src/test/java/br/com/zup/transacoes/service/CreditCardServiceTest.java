package br.com.zup.transacoes.service;

import br.com.zup.transacoes.builder.CreditCardBuilder;
import br.com.zup.transacoes.model.CreditCard;
import br.com.zup.transacoes.repository.CreditCardRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest
@ActiveProfiles("test")
@ExtendWith(SpringExtension.class)
public class CreditCardServiceTest {

    @MockBean
    private CreditCardRepository creditCardRepository;

    @Qualifier("creditCardService")
    @Autowired
    private CreditCardService service;

    @BeforeEach
    public void setup() {
        CreditCard creditCard = new CreditCardBuilder()
                .withId("b0012b90-42c8-40e6-903b-64acb3aa649b")
                .withEmail("test@email.com")
                .build();

        when(creditCardRepository.findById(UUID.fromString("b0012b90-42c8-40e6-903b-64acb3aa649b")))
                .thenReturn(Optional.ofNullable(creditCard));
    }

    @Test
    public void testCheckCreditCardExists() {
        CreditCard creditCard = service.checkCreditCardExists(UUID.fromString("b0012b90-42c8-40e6-903b-64acb3aa649b"));

        assertNotNull(creditCard);
        assertEquals("b0012b90-42c8-40e6-903b-64acb3aa649b", creditCard.getId());
        assertEquals("test@email.com", creditCard.getEmail());
    }

    @Test
    public void shouldReturnNullIfCreditCardNotExists() {
        CreditCard creditCard = service.checkCreditCardExists(UUID.fromString("c63fd0e0-eccb-4af3-9d49-39cde0ffdaf1"));

        assertNull(creditCard);
    }
}
