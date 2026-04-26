package domain;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ProprietaireTest {

    @Test
    void creeProprietaireValide() {
        Proprietaire p = new Proprietaire("Alice");
        assertEquals("Alice", p.value());
    }

    @Test
    void proprietaireVideLeveException() {
        assertThrows(IllegalArgumentException.class, () -> new Proprietaire(""));
    }

    @Test
    void proprietaireNullLeveException() {
        assertThrows(IllegalArgumentException.class, () -> new Proprietaire(null));
    }

    @Test
    void deuxProprietairesIdentiquesSontEgaux() {
        Proprietaire p1 = new Proprietaire("Alice");
        Proprietaire p2 = new Proprietaire("Alice");
        assertEquals(p1, p2);
    }

    @Test
    void toStringRetourneLaValeur() {
        Proprietaire p = new Proprietaire("Alice");
        assertEquals("Alice", p.toString());
    }
}
