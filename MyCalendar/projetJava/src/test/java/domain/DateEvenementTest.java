package domain;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class DateEvenementTest {

    @Test
    void creeDateValide() {
        DateEvenement d = new DateEvenement(LocalDate.of(2024, 6, 15));
        assertEquals(LocalDate.of(2024, 6, 15), d.value());
    }

    @Test
    void dateNullLeveException() {
        assertThrows(IllegalArgumentException.class, () -> new DateEvenement(null));
    }

    @Test
    void deuxDatesIdentiquesSontEgales() {
        DateEvenement d1 = new DateEvenement(LocalDate.of(2024, 6, 15));
        DateEvenement d2 = new DateEvenement(LocalDate.of(2024, 6, 15));
        assertEquals(d1, d2);
    }

    @Test
    void deuxDatesDifferentesNonEgales() {
        DateEvenement d1 = new DateEvenement(LocalDate.of(2024, 6, 15));
        DateEvenement d2 = new DateEvenement(LocalDate.of(2024, 6, 16));
        assertNotEquals(d1, d2);
    }

    @Test
    void toStringRetourneLaDateFormatee() {
        DateEvenement d = new DateEvenement(LocalDate.of(2024, 6, 15));
        assertEquals("2024-06-15", d.toString());
    }
}
