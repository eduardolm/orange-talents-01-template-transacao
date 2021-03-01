package br.com.zup.transacoes.dto.response;

import br.com.zup.transacoes.model.CreditCard;
import br.com.zup.transacoes.model.Store;
import br.com.zup.transacoes.model.Transaction;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class TransactionDetailsDto {

    private String id;
    private BigDecimal amount;
    private Store store;
    private CreditCard creditCard;
    private LocalDateTime createdAt;

    public TransactionDetailsDto(Transaction transaction) {
        this.id = transaction.getId();
        this.amount = transaction.getAmount();
        this.store = transaction.getStore();
        this.creditCard = transaction.getCreditCard();
        this.createdAt = transaction.getCreatedAt();
    }

    public String getId() {
        return id;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public Store getStore() {
        return store;
    }

    public CreditCard getCreditCard() {
        return creditCard;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    @Override
    public String toString() {
        return "TransactionDetailsDto{" +
                "id='" + id + '\'' +
                ", amount=" + amount +
                ", store=" + store +
                ", creditCard=" + creditCard +
                ", createdAt=" + createdAt +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof TransactionDetailsDto)) return false;

        TransactionDetailsDto that = (TransactionDetailsDto) o;

        if (getId() != null ? !getId().equals(that.getId()) : that.getId() != null) return false;
        if (getAmount() != null ? !getAmount().equals(that.getAmount()) : that.getAmount() != null) return false;
        if (getStore() != null ? !getStore().equals(that.getStore()) : that.getStore() != null) return false;
        if (getCreditCard() != null ? !getCreditCard().equals(that.getCreditCard()) : that.getCreditCard() != null)
            return false;
        return getCreatedAt() != null ? getCreatedAt().equals(that.getCreatedAt()) : that.getCreatedAt() == null;
    }

    @Override
    public int hashCode() {
        int result = getId() != null ? getId().hashCode() : 0;
        result = 31 * result + (getAmount() != null ? getAmount().hashCode() : 0);
        result = 31 * result + (getStore() != null ? getStore().hashCode() : 0);
        result = 31 * result + (getCreditCard() != null ? getCreditCard().hashCode() : 0);
        result = 31 * result + (getCreatedAt() != null ? getCreatedAt().hashCode() : 0);
        return result;
    }
}
