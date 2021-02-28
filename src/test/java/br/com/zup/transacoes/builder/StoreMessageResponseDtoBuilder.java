package br.com.zup.transacoes.builder;

import br.com.zup.transacoes.dto.response.StoreMessageResponseDto;

public class StoreMessageResponseDtoBuilder {

    private String name;
    private String city;
    private String address;

    public StoreMessageResponseDtoBuilder withName(String name) {
        this.name = name;
        return this;
    }

    public StoreMessageResponseDtoBuilder withCity(String city) {
        this.city = city;
        return this;
    }

    public StoreMessageResponseDtoBuilder withAddress(String address) {
        this.address = address;
        return this;
    }

    public StoreMessageResponseDto build() {
        return new StoreMessageResponseDto(name, city, address);
    }
}
