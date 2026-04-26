package domain;

import java.time.LocalTime;
import java.util.Objects;
import java.util.Optional;

public final class HeureDebut {

    private final LocalTime value;

    public HeureDebut(LocalTime value) {
        this.value = Optional.ofNullable(value)
                .orElseThrow(() -> new IllegalArgumentException("L'heure ne peut pas être nulle"));
    }

    public LocalTime value() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        return o instanceof HeureDebut that && Objects.equals(value, that.value);
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
