package br.com.zup.transacoes.builder;

import br.com.zup.transacoes.model.Store;

public class StoreBuilder {

    private String name;
    private String city;
    private String address;

    public StoreBuilder withName(String name) {
        this.name = name;
        return this;
    }

    public StoreBuilder withCity(String city) {
        this.city = city;
        return this;
    }

    public StoreBuilder withAddress(String address) {
        this.address = address;
        return this;
    }

    public Store build() {
        return new Store(name, city, address);
    }
}
