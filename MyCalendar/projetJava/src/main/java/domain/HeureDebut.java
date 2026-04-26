package domain;

import java.time.LocalTime;
import java.util.Objects;

public final class HeureDebut {

    private final LocalTime value;

    public HeureDebut(LocalTime value) {
        if (value == null) {
            throw new IllegalArgumentException("L'heure ne peut pas être nulle");
        }
        this.value = value;
    }

    public LocalTime value() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        HeureDebut that = (HeureDebut) o;
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
