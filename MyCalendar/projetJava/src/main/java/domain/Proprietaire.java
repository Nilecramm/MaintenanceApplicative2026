package domain;

import java.util.Objects;
import java.util.Optional;

public final class Proprietaire {

    private final String value;

    public Proprietaire(String value) {
        this.value = Optional.ofNullable(value)
                .filter(v -> !v.isBlank())
                .orElseThrow(() -> new IllegalArgumentException("Le propriétaire ne peut pas être vide"));
    }

    public String value() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        return o instanceof Proprietaire that && Objects.equals(value, that.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }

    @Override
    public String toString() {
        return value;
    }
}
