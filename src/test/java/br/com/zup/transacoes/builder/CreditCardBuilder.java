package br.com.zup.transacoes.builder;

import br.com.zup.transacoes.model.CreditCard;

import javax.validation.Valid;
import java.util.UUID;

public class CreditCardBuilder {

    private UUID id;
    private String email;

    public CreditCardBuilder withId(@Valid UUID id) {
        this.id = id;
        return this;
    }

    public CreditCardBuilder withEmail(@Valid String email) {
        this.email = email;
        return this;
    }

    public CreditCard build() {
        return new CreditCard(id, email);
    }
}
