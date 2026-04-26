package domain;

import java.util.Objects;
import java.util.Optional;

public final class DelaiRappel {

    private final int minutes;

    public DelaiRappel(int minutes) {
        this.minutes = Optional.of(minutes)
                .filter(m -> m > 0)
                .orElseThrow(() -> new IllegalArgumentException("Le délai doit être positif"));
    }

    public int value() {
        return minutes;
    }

    @Override
    public boolean equals(Object o) {
        return o instanceof DelaiRappel that && minutes == that.minutes;
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
