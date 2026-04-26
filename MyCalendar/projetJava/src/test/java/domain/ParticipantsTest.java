package domain;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ParticipantsTest {

    @Test
    void creeParticipantsValides() {
        Participants p = new Participants("Alice", "Bob");
        assertEquals(2, p.value().size());
    }

    @Test
    void creeParticipantsAvecListe() {
        List<String> noms = Arrays.asList("Alice", "Bob", "Charlie");
        Participants p = new Participants(noms);
        assertEquals(3, p.value().size());
    }

    @Test
    void listeVideLeveException() {
        assertThrows(IllegalArgumentException.class, () -> new Participants());
    }

    @Test
    void listeNullLeveException() {
        assertThrows(IllegalArgumentException.class, () -> new Participants((List<String>) null));
    }

    @Test
    void contientParticipant() {
        Participants p = new Participants("Alice", "Bob");
        assertTrue(p.contient("Alice"));
        assertFalse(p.contient("Charlie"));
    }

    @Test
    void deuxParticipantsIdentiquesSontEgaux() {
        Participants p1 = new Participants("Alice", "Bob");
        Participants p2 = new Participants("Alice", "Bob");
        assertEquals(p1, p2);
    }

    @Test
    void toStringJoinLesNoms() {
        Participants p = new Participants("Alice", "Bob");
        assertEquals("Alice, Bob", p.toString());
    }
}
