package domain;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.Arrays;

public final class Participants {

    private final List<String> noms;

    public Participants(List<String> noms) {
        this.noms = Optional.ofNullable(noms)
                .filter(n -> !n.isEmpty())
                .map(Collections::unmodifiableList)
                .orElseThrow(() -> new IllegalArgumentException("Il doit y avoir au moins un participant"));
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
        return o instanceof Participants that && Objects.equals(noms, that.noms);
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
