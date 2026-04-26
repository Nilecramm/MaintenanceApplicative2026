package domain;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DureeEvenementTest {

    @Test
    void creeDureeValide() {
        DureeEvenement duree = new DureeEvenement(60);
        assertEquals(60, duree.value());
    }

    @Test
    void dureeZeroEstValide() {
        DureeEvenement duree = new DureeEvenement(0);
        assertEquals(0, duree.value());
    }

    @Test
    void dureeNegativeLeveException() {
        assertThrows(IllegalArgumentException.class, () -> new DureeEvenement(-1));
    }

    @Test
    void deuxDureesIdentiquesSontEgales() {
        DureeEvenement d1 = new DureeEvenement(45);
        DureeEvenement d2 = new DureeEvenement(45);
        assertEquals(d1, d2);
    }
}
