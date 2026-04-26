package domain;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LieuEvenementTest {

    @Test
    void creeLieuValide() {
        LieuEvenement lieu = new LieuEvenement("Salle A");
        assertEquals("Salle A", lieu.value());
    }

    @Test
    void lieuVideLeveException() {
        assertThrows(IllegalArgumentException.class, () -> new LieuEvenement(""));
    }

    @Test
    void lieuNullLeveException() {
        assertThrows(IllegalArgumentException.class, () -> new LieuEvenement(null));
    }

    @Test
    void deuxLieuxIdentiquesSontEgaux() {
        LieuEvenement l1 = new LieuEvenement("Salle B");
        LieuEvenement l2 = new LieuEvenement("Salle B");
        assertEquals(l1, l2);
    }
}
