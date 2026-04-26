package domain;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Arrays;

public final class Participants {

    private final List<String> noms;

    public Participants(List<String> noms) {
        if (noms == null || noms.isEmpty()) {
            throw new IllegalArgumentException("Il doit y avoir au moins un participant");
        }
        this.noms = Collections.unmodifiableList(noms);
    }

    public Participants(String... noms) {
        this(Arrays.asList(noms));
    }

    public List<String> value() {
        return noms;
    }

    public boolean contient(String nom) {
        return noms.contains(nom);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Participants that = (Participants) o;
        return Objects.equals(noms, that.noms);
    }

    @Override
    public int hashCode() {
        return Objects.hash(noms);
    }

    @Override
    public String toString() {
        return String.join(", ", noms);
    }
}
