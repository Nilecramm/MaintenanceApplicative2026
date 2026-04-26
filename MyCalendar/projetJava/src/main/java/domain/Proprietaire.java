package domain;

import java.util.Objects;

public final class Proprietaire {

    private final String value;

    public Proprietaire(String value) {
        if (value == null || value.isBlank()) {
            throw new IllegalArgumentException("Le propriétaire ne peut pas être vide");
        }
        this.value = value;
    }

    public String value() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Proprietaire that = (Proprietaire) o;
        return Objects.equals(value, that.value);
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
