package co.spribe.test.db.entities;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "currencies")
public class Currency extends BaseEntity {

    @Column(nullable = false, length = 3)
    private String code;

    @Column(length = 50)
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), code, name);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        var currency = (Currency) o;
        return super.equals(currency) &&
            Objects.equals(code, currency.code) &&
            Objects.equals(name, currency.name);
    }
}
