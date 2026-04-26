package domain;

import java.util.Objects;
import java.util.Optional;

public final class FrequenceRepetition {

    private final int jours;

    public FrequenceRepetition(int jours) {
        this.jours = Optional.of(jours)
                .filter(j -> j > 0)
                .orElseThrow(() -> new IllegalArgumentException("La fréquence doit être positive"));
    }

    public int value() {
        return jours;
    }

    @Override
    public boolean equals(Object o) {
        return o instanceof FrequenceRepetition that && jours == that.jours;
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
