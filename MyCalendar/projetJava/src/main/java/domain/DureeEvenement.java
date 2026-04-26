package domain;

import java.util.Objects;
import java.util.Optional;

public final class DureeEvenement {

    private final int minutes;

    public DureeEvenement(int minutes) {
        this.minutes = Optional.of(minutes)
                .filter(m -> m >= 0)
                .orElseThrow(() -> new IllegalArgumentException("La durée ne peut pas être négative"));
    }

    public int value() {
        return minutes;
    }

    @Override
    public boolean equals(Object o) {
        return o instanceof DureeEvenement that && minutes == that.minutes;
    }

    @Override
    public int hashCode() {
        return Objects.hash(minutes);
    }

    @Override
    public String toString() {
        return String.valueOf(minutes);
    }
}
