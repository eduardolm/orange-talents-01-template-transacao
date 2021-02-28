package br.com.zup.transacoes.dto.response;

import br.com.zup.transacoes.model.Store;

public class StoreMessageResponseDto {

    private String nome;
    private String cidade;
    private String endereco;

    public StoreMessageResponseDto(String nome, String cidade, String endereco) {
        this.nome = nome;
        this.cidade = cidade;
        this.endereco = endereco;
    }

    @Deprecated
    public StoreMessageResponseDto() {
    }

    public String getNome() {
        return nome;
    }

    public String getCidade() {
        return cidade;
    }

    public String getEndereco() {
        return endereco;
    }

    @Override
    public String toString() {
        return "StoreMessageResponseDto{" +
                "name='" + nome + '\'' +
                ", cidade='" + cidade + '\'' +
                ", endereco='" + endereco + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof StoreMessageResponseDto)) return false;

        StoreMessageResponseDto that = (StoreMessageResponseDto) o;

        if (getNome() != null ? !getNome().equals(that.getNome()) : that.getNome() != null) return false;
        if (getCidade() != null ? !getCidade().equals(that.getCidade()) : that.getCidade() != null) return false;
        return getEndereco() != null ? getEndereco().equals(that.getEndereco()) : that.getEndereco() == null;
    }

    @Override
    public int hashCode() {
        int result = getNome() != null ? getNome().hashCode() : 0;
        result = 31 * result + (getCidade() != null ? getCidade().hashCode() : 0);
        result = 31 * result + (getEndereco() != null ? getEndereco().hashCode() : 0);
        return result;
    }

    public Store toModel() {
        return new Store(nome, cidade, endereco);
    }
}
