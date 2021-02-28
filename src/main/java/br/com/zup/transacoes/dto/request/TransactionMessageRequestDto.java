package br.com.zup.transacoes.dto.request;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

public class TransactionMessageRequestDto {

    @NotBlank(message = "Id é obrigatório.")
    private String id;

    @NotBlank(message = "E-mail é obrigatório.")
    @Email(message = "Formato inválido.")
    private String email;

    public TransactionMessageRequestDto(String id, String email) {
        this.id = id;
        this.email = email;
    }

    @Deprecated
    public TransactionMessageRequestDto() {
    }

    public String getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    @Override
    public String toString() {
        return "TransactionMessageRequestDto{" +
                "id='" + id + '\'' +
                ", email='" + email + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof TransactionMessageRequestDto)) return false;

        TransactionMessageRequestDto that = (TransactionMessageRequestDto) o;

        if (getId() != null ? !getId().equals(that.getId()) : that.getId() != null) return false;
        return getEmail() != null ? getEmail().equals(that.getEmail()) : that.getEmail() == null;
    }

    @Override
    public int hashCode() {
        int result = getId() != null ? getId().hashCode() : 0;
        result = 31 * result + (getEmail() != null ? getEmail().hashCode() : 0);
        return result;
    }
}
