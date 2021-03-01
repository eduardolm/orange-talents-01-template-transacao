package br.com.zup.transacoes.dto.response;

import br.com.zup.transacoes.model.Store;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class StoreMessageResponseDtoTest {
    @Test
    public void testToString() {
        assertEquals("StoreMessageResponseDto{name='null', cidade='null', endereco='null'}",
                (new StoreMessageResponseDto()).toString());
    }

    @Test
    public void testEquals() {
        assertNotEquals((new StoreMessageResponseDto()), "o");
    }

    @Test
    public void testEquals2() {
        StoreMessageResponseDto storeMessageResponseDto = new StoreMessageResponseDto();

        assertEquals(new StoreMessageResponseDto(), storeMessageResponseDto);
    }

    @Test
    public void testEquals3() {
        StoreMessageResponseDto storeMessageResponseDto = new StoreMessageResponseDto();

        assertNotEquals(new StoreMessageResponseDto("Nome", "Cidade", "Endereco"), storeMessageResponseDto);
    }

    @Test
    public void testHashCode() {
        assertEquals(0, (new StoreMessageResponseDto()).hashCode());
        assertEquals(-2020726242, (new StoreMessageResponseDto("Nome", "Cidade", "Endereco")).hashCode());
    }

    @Test
    public void testToModel() {
        Store actualToModelResult = (new StoreMessageResponseDto()).toModel();

        assertNull(actualToModelResult.getAddress());
        assertNull(actualToModelResult.getCity());
        assertNull(actualToModelResult.getName());
    }
}

