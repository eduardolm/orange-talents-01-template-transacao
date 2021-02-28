package br.com.zup.transacoes.model;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name = "estabelecimentos")
public class Store {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "O nome do estabelecimento é obrigatório.")
    private String name;

    @NotBlank(message = "O nome da cidade é obrigatório.")
    private String city;

    @NotBlank(message = "O endereço do estabelecimento é obrigatório.")
    private String address;

    @OneToOne(mappedBy = "store")
    private Transaction transaction;

    @Deprecated
    public Store() {
    }

    public Store(@NotBlank(message = "O nome do estabelecimento é obrigatório.") String name,
                 @NotBlank(message = "O nome da cidade é obrigatório.") String city,
                 @NotBlank(message = "O endereço do estabelecimento é obrigatório.") String address) {

        this.name = name;
        this.city = city;
        this.address = address;
    }

    @Override
    public String toString() {
        return "Store{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", city='" + city + '\'' +
                ", address='" + address + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Store)) return false;

        Store store = (Store) o;

        if (getName() != null ? !getName().equals(store.getName()) : store.getName() != null) return false;
        if (getCity() != null ? !getCity().equals(store.getCity()) : store.getCity() != null) return false;
        return getAddress() != null ? getAddress().equals(store.getAddress()) : store.getAddress() == null;
    }

    @Override
    public int hashCode() {
        int result = getName() != null ? getName().hashCode() : 0;
        result = 31 * result + (getCity() != null ? getCity().hashCode() : 0);
        result = 31 * result + (getAddress() != null ? getAddress().hashCode() : 0);
        return result;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public String getCity() {
        return city;
    }

    public String getAddress() {
        return address;
    }
}
