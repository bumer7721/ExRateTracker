package co.spribe.test.facades.data;

import java.util.Objects;

public class CurrencyDTO extends BaseDTO {

    private String code;
    private String name;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

        var that = (CurrencyDTO) o;
        return super.equals(o) &&
                Objects.equals(code, that.code) &&
                Objects.equals(name, that.name);
    }
}
