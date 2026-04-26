package domain;

import java.util.Objects;

public final class TitreEvenement {

    private final String value;

    public TitreEvenement(String value) {
        if (value == null || value.isBlank()) {
            throw new IllegalArgumentException("Le titre ne peut pas être vide");
        }
        this.value = value;
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
