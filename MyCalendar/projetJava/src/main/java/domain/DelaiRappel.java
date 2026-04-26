package domain;

import java.util.Objects;

public final class DelaiRappel {

    private final int minutes;

    public DelaiRappel(int minutes) {
        if (minutes <= 0) {
            throw new IllegalArgumentException("Le délai doit être positif");
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
        DelaiRappel that = (DelaiRappel) o;
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
