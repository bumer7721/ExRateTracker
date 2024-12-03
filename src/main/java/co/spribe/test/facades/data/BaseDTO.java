package co.spribe.test.facades.data;

import java.util.Objects;

public abstract class BaseDTO {

    protected Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        var base = (BaseDTO) o;
        return Objects.equals(id, base.id);
    }
}
