package domain;

import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

public final class EventId {

    private final UUID value;

    public EventId() {
        this.value = UUID.randomUUID();
    }

    public EventId(UUID value) {
        this.value = Optional.ofNullable(value)
                .orElseThrow(() -> new IllegalArgumentException("L'identifiant ne peut pas être null"));
    }

    public UUID value() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        return o instanceof EventId that && Objects.equals(value, that.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }

    @Override
    public String toString() {
        return value.toString();
    }
}
