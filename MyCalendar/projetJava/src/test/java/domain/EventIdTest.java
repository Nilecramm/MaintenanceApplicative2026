package domain;

import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class EventIdTest {

    @Test
    void genereIdUniqueAutomatiquement() {
        EventId id1 = new EventId();
        EventId id2 = new EventId();
        assertNotEquals(id1, id2);
    }

    @Test
    void creeAvecUuidSpecifique() {
        UUID uuid = UUID.randomUUID();
        EventId id = new EventId(uuid);
        assertEquals(uuid, id.value());
    }

    @Test
    void uuidNullLeveException() {
        assertThrows(IllegalArgumentException.class, () -> new EventId(null));
    }

    @Test
    void deuxIdsIdentiquesSontEgaux() {
        UUID uuid = UUID.randomUUID();
        EventId id1 = new EventId(uuid);
        EventId id2 = new EventId(uuid);
        assertEquals(id1, id2);
    }
}
