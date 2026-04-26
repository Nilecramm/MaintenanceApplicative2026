package domain;

import java.util.Objects;
import java.util.Optional;

public final class TitreEvenement {

    private final String value;

    public TitreEvenement(String value) {
        this.value = Optional.ofNullable(value)
                .filter(v -> !v.isBlank())
                .orElseThrow(() -> new IllegalArgumentException("Le titre ne peut pas être vide"));
    }

    public String value() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        return o instanceof TitreEvenement that && Objects.equals(value, that.value);
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
