package br.com.zup.transacoes.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "cartoes_de_credito")
public class CreditCard {

    @Id
    private String id;

    @NotBlank(message = "E-mail é obrigatório.")
    @Email(message = "Formato de e-mail inválido.")
    private String email;

    @OneToMany(mappedBy = "creditCard", cascade = CascadeType.PERSIST)
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private List<Transaction> transactions = new ArrayList<>();

    @Deprecated
    public CreditCard() {
    }

    public CreditCard(String id,
                      @NotBlank(message = "E-mail é obrigatório.")
                      @Email(message = "Formato de e-mail inválido.") String email) {

        this.id = id;
        this.email = email;
    }

    public String getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public List<Transaction> getTransactions() {
        return transactions;
    }

    @Override
    public String toString() {
        return "CreditCard{" +
                "id=" + id +
                ", email=" + email +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CreditCard)) return false;

        CreditCard that = (CreditCard) o;

        if (getId() != null ? !getId().equals(that.getId()) : that.getId() != null) return false;
        return getEmail() != null ? getEmail().equals(that.getEmail()) : that.getEmail() == null;
    }

    @Override
    public int hashCode() {
        int result = getId() != null ? getId().hashCode() : 0;
        result = 31 * result + (getEmail() != null ? getEmail().hashCode() : 0);
        return result;
    }

    public void addTransaction(Transaction transaction) {
        this.transactions.add(transaction);
    }
}
