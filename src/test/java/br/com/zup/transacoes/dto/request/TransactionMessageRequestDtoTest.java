package br.com.zup.transacoes.dto.request;

import org.junit.jupiter.api.Test;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

@ActiveProfiles("test")
public class TransactionMessageRequestDtoTest {

    @Test
    public void testOverloadedConstructor() {
        TransactionMessageRequestDto request = new TransactionMessageRequestDto("123456", "test@email.com");

        assertEquals("123456", request.getId());
        assertEquals("test@email.com", request.getEmail());
    }

    @Test
    public void testToString() {
        TransactionMessageRequestDto request = new TransactionMessageRequestDto("123456", "test@email.com");

        assertEquals("TransactionMessageRequestDto{id='123456', email='test@email.com'}", request.toString());
    }

    @Test
    public void testEquals() {
        TransactionMessageRequestDto request1 = new TransactionMessageRequestDto("123456", "test@email.com");
        TransactionMessageRequestDto request2 = new TransactionMessageRequestDto("123456", "test@email.com");
        TransactionMessageRequestDto request3 = new TransactionMessageRequestDto();
        TransactionMessageRequestDto request4 = new TransactionMessageRequestDto();

        assertEquals(request1, request2);
        assertEquals(request3, request4);;
        assertNotEquals(request1, request4);
        assertNotEquals(request2, request3);
    }

    @Test
    public void testHashCode() {
        TransactionMessageRequestDto request1 = new TransactionMessageRequestDto("123456", "test@email.com");
        TransactionMessageRequestDto request2 = new TransactionMessageRequestDto("123456", "test@email.com");
        TransactionMessageRequestDto request3 = new TransactionMessageRequestDto();
        TransactionMessageRequestDto request4 = new TransactionMessageRequestDto();

        assertEquals(request1.hashCode(), request2.hashCode());
        assertEquals(request3.hashCode(), request4.hashCode());;
        assertNotEquals(request1.hashCode(), request4.hashCode());
        assertNotEquals(request2.hashCode(), request3.hashCode());
    }
}
