package domain;

import java.util.Objects;

public final class FrequenceRepetition {

    private final int jours;

    public FrequenceRepetition(int jours) {
        if (jours <= 0) {
            throw new IllegalArgumentException("La fréquence doit être positive");
        }
        this.jours = jours;
    }

    public int value() {
        return jours;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FrequenceRepetition that = (FrequenceRepetition) o;
        return jours == that.jours;
    }

    @Override
    public int hashCode() {
        return Objects.hash(jours);
    }

    @Override
    public String toString() {
        return String.valueOf(jours);
    }
}
