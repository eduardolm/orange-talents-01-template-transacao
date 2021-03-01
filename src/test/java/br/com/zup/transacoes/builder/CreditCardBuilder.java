package br.com.zup.transacoes.builder;

import br.com.zup.transacoes.model.CreditCard;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import java.util.UUID;

public class CreditCardBuilder {

    private String id;
    private String email;

    public CreditCardBuilder withId(@NotBlank String id) {
        this.id = id;
        return this;
    }

    public CreditCardBuilder withEmail(@NotBlank String email) {
        this.email = email;
        return this;
    }

    public CreditCard build() {
        return new CreditCard(id, email);
    }
}
