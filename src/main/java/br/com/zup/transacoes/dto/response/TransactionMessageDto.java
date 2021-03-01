package br.com.zup.transacoes.dto.response;

import br.com.zup.transacoes.model.CreditCard;
import br.com.zup.transacoes.model.Transaction;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

public class TransactionMessageDto {

    private String id;
    private BigDecimal valor;
    private StoreMessageResponseDto estabelecimento;
    private CreditCard cartao;
    private LocalDateTime efetivadaEm;

    public TransactionMessageDto(String id, BigDecimal valor, StoreMessageResponseDto estabelecimento, CreditCard cartao,
                                 LocalDateTime efetivadaEm) {

        this.id = id;
        this.valor = valor;
        this.estabelecimento = estabelecimento;
        this.cartao = cartao;
        this.efetivadaEm = efetivadaEm;
    }

    @Deprecated
    public TransactionMessageDto() {
    }

    public String getId() {
        return id;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public StoreMessageResponseDto getEstabelecimento() {
        return estabelecimento;
    }

    public CreditCard getCartao() {
        return cartao;
    }

    public LocalDateTime getEfetivadaEm() {
        return efetivadaEm;
    }

    @Override
    public String toString() {
        return "TransactionMessageDto{" +
                "id=" + id +
                ", valor=" + valor +
                ", estabelecimento=" + estabelecimento +
                ", cartao=" + cartao +
                ", efetivadaEm=" + efetivadaEm +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof TransactionMessageDto)) return false;

        TransactionMessageDto that = (TransactionMessageDto) o;

        if (getId() != null ? !getId().equals(that.getId()) : that.getId() != null) return false;
        if (getValor() != null ? !getValor().equals(that.getValor()) : that.getValor() != null) return false;
        if (getEstabelecimento() != null ? !getEstabelecimento().equals(that.getEstabelecimento()) : that.getEstabelecimento() != null)
            return false;
        if (getCartao() != null ? !getCartao().equals(that.getCartao()) : that.getCartao() != null) return false;
        return getEfetivadaEm() != null ? getEfetivadaEm().equals(that.getEfetivadaEm()) : that.getEfetivadaEm() == null;
    }

    @Override
    public int hashCode() {
        int result = getId() != null ? getId().hashCode() : 0;
        result = 31 * result + (getValor() != null ? getValor().hashCode() : 0);
        result = 31 * result + (getEstabelecimento() != null ? getEstabelecimento().hashCode() : 0);
        result = 31 * result + (getCartao() != null ? getCartao().hashCode() : 0);
        result = 31 * result + (getEfetivadaEm() != null ? getEfetivadaEm().hashCode() : 0);
        return result;
    }

    public Transaction toModel() {
        return new Transaction(id, valor, estabelecimento.toModel(), cartao, efetivadaEm);
    }
}
