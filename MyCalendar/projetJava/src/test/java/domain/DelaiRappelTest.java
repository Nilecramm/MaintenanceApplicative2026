package domain;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DelaiRappelTest {

    @Test
    void creeDelaiValide() {
        DelaiRappel d = new DelaiRappel(15);
        assertEquals(15, d.value());
    }

    @Test
    void delaiZeroLeveException() {
        assertThrows(IllegalArgumentException.class, () -> new DelaiRappel(0));
    }

    @Test
    void delaiNegatifLeveException() {
        assertThrows(IllegalArgumentException.class, () -> new DelaiRappel(-5));
    }

    @Test
    void deuxDelaisIdentiquesSontEgaux() {
        DelaiRappel d1 = new DelaiRappel(10);
        DelaiRappel d2 = new DelaiRappel(10);
        assertEquals(d1, d2);
    }

    @Test
    void toStringRetourneLaValeur() {
        DelaiRappel d = new DelaiRappel(10);
        assertEquals("10", d.toString());
    }
}
