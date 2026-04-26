package domain;

import org.junit.jupiter.api.Test;

import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.*;

class HeureDebutTest {

    @Test
    void creeHeureValide() {
        HeureDebut h = new HeureDebut(LocalTime.of(10, 30));
        assertEquals(LocalTime.of(10, 30), h.value());
    }

    @Test
    void heureNullLeveException() {
        assertThrows(IllegalArgumentException.class, () -> new HeureDebut(null));
    }

    @Test
    void deuxHeuresIdentiquesSontEgales() {
        HeureDebut h1 = new HeureDebut(LocalTime.of(10, 30));
        HeureDebut h2 = new HeureDebut(LocalTime.of(10, 30));
        assertEquals(h1, h2);
    }

    @Test
    void deuxHeuresDifferentesNonEgales() {
        HeureDebut h1 = new HeureDebut(LocalTime.of(10, 30));
        HeureDebut h2 = new HeureDebut(LocalTime.of(11, 0));
        assertNotEquals(h1, h2);
    }

    @Test
    void toStringRetourneLHeureFormatee() {
        HeureDebut h = new HeureDebut(LocalTime.of(10, 30));
        assertEquals("10:30", h.toString());
    }
}
