package domain;

import java.time.LocalDate;
import java.util.Objects;
import java.util.Optional;

public final class DateEvenement {

    private final LocalDate value;

    public DateEvenement(LocalDate value) {
        this.value = Optional.ofNullable(value)
                .orElseThrow(() -> new IllegalArgumentException("La date ne peut pas être nulle"));
    }

    public LocalDate value() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        return o instanceof DateEvenement that && Objects.equals(value, that.value);
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
