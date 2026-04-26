import Event.Rappel;
import domain.DureeEvenement;
import domain.Proprietaire;
import domain.TitreEvenement;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class TestRappel {

    private final LocalDateTime date = LocalDateTime.of(2024, 6, 15, 10, 30);
    private final Proprietaire alice = new Proprietaire("Alice");

    @Test
    void description_rappel() {
        Rappel e = new Rappel(
                new TitreEvenement("Prendre médicaments"),
                alice,
                date,
                new DureeEvenement(5),
                15
        );
        assertEquals("Rappel : Prendre médicaments dans 15 minutes", e.description());
    }

    @Test
    void estDansPeriode_rappelDansLaPeriode() {
        Rappel e = new Rappel(
                new TitreEvenement("Prendre médicaments"),
                alice,
                date,
                new DureeEvenement(5),
                10
        );
        assertTrue(e.estDansPeriode(date.minusHours(1), date.plusHours(1)));
    }

    @Test
    void estDansPeriode_rappelHorsPeriode() {
        Rappel e = new Rappel(
                new TitreEvenement("Prendre médicaments"),
                alice,
                date,
                new DureeEvenement(5),
                10
        );
        assertFalse(e.estDansPeriode(date.plusDays(1), date.plusDays(2)));
    }

    @Test
    void rappel_aUnIdUnique() {
        Rappel e1 = new Rappel(new TitreEvenement("A"), alice, date, new DureeEvenement(5), 10);
        Rappel e2 = new Rappel(new TitreEvenement("A"), alice, date, new DureeEvenement(5), 10);
        assertNotEquals(e1.id(), e2.id());
    }

    @Test
    void rappel_dateFinCalculeeCorrectement() {
        Rappel e = new Rappel(new TitreEvenement("A"), alice, date, new DureeEvenement(15), 10);
        assertEquals(date.plusMinutes(15), e.dateFin());
    }
}
