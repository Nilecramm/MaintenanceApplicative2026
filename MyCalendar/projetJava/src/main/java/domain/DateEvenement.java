package domain;

import java.time.LocalDate;
import java.util.Objects;

public final class DateEvenement {

    private final LocalDate value;

    public DateEvenement(LocalDate value) {
        if (value == null) {
            throw new IllegalArgumentException("La date ne peut pas être nulle");
        }
        this.value = value;
    }

    public LocalDate value() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DateEvenement that = (DateEvenement) o;
        return Objects.equals(value, that.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }

    @Override
    public String toString() {
        return value.toString();
    }
}
