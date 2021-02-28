package br.com.zup.transacoes.model;

import br.com.zup.transacoes.builder.StoreBuilder;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ActiveProfiles("test")
public class StoreTest {

    private Store store;

    @BeforeEach
    public void setup() {
        Store store = new StoreBuilder()
                .withName("Levi's")
                .withCity("São Paulo")
                .withAddress("Rua Pedroso de Morais, 4356")
                .build();

        store.setId(1L);

        this.store = store;
    }

    @Test
    public void shouldCreateStoreInstanceWithOverloadedConstructor() {
        assertEquals("Levi's", store.getName());
        assertEquals("São Paulo", store.getCity());
        assertEquals("Rua Pedroso de Morais, 4356", store.getAddress());
        assertEquals(1L, store.getId());
    }

    @Test
    public void testToString() {
        assertEquals("Store{id=1, name=\'Levi's\', city=\'São Paulo\', address=\'Rua Pedroso de Morais, 4356\'}"
                , store.toString());
    }

    @Test
    public void testEquals() {
        Store store2 = new StoreBuilder()
                .withName("Levi's")
                .withCity("São Paulo")
                .withAddress("Rua Pedroso de Morais, 4356")
                .build();

        assertEquals(store, store2);
        assertEquals(store.hashCode(), store2.hashCode());
    }
}
