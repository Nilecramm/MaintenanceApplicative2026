package domain;

import java.util.Objects;

public final class DureeEvenement {

    private final int minutes;

    public DureeEvenement(int minutes) {
        if (minutes < 0) {
            throw new IllegalArgumentException("La durée ne peut pas être négative");
        }
        this.minutes = minutes;
    }

    public int value() {
        return minutes;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DureeEvenement that = (DureeEvenement) o;
        return minutes == that.minutes;
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
