package domain;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TitreEvenementTest {

    @Test
    void creeTitreValide() {
        TitreEvenement titre = new TitreEvenement("Réunion sprint");
        assertEquals("Réunion sprint", titre.value());
    }

    @Test
    void titreVideLeveException() {
        assertThrows(IllegalArgumentException.class, () -> new TitreEvenement(""));
    }

    @Test
    void titreNullLeveException() {
        assertThrows(IllegalArgumentException.class, () -> new TitreEvenement(null));
    }

    @Test
    void deuxTitresIdentiquesSontEgaux() {
        TitreEvenement t1 = new TitreEvenement("Meeting");
        TitreEvenement t2 = new TitreEvenement("Meeting");
        assertEquals(t1, t2);
    }

    @Test
    void toStringRetourneLaValeur() {
        TitreEvenement titre = new TitreEvenement("Test");
        assertEquals("Test", titre.toString());
    }
}
