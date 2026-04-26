import Event.Rappel;
import domain.DateEvenement;
import domain.DureeEvenement;
import domain.HeureDebut;
import domain.Proprietaire;
import domain.TitreEvenement;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.*;

class TestRappel {

    private final LocalDateTime dateTime = LocalDateTime.of(2024, 6, 15, 10, 30);
    private final DateEvenement date = new DateEvenement(LocalDate.of(2024, 6, 15));
    private final HeureDebut heure = new HeureDebut(LocalTime.of(10, 30));
    private final Proprietaire alice = new Proprietaire("Alice");

    @Test
    void description_rappel() {
        Rappel e = new Rappel(
                new TitreEvenement("Prendre médicaments"),
                alice,
                date,
                heure,
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
                heure,
                new DureeEvenement(5),
                10
        );
        assertTrue(e.estDansPeriode(dateTime.minusHours(1), dateTime.plusHours(1)));
    }

    @Test
    void estDansPeriode_rappelHorsPeriode() {
        Rappel e = new Rappel(
                new TitreEvenement("Prendre médicaments"),
                alice,
                date,
                heure,
                new DureeEvenement(5),
                10
        );
        assertFalse(e.estDansPeriode(dateTime.plusDays(1), dateTime.plusDays(2)));
    }

    @Test
    void rappel_aUnIdUnique() {
        Rappel e1 = new Rappel(new TitreEvenement("A"), alice, date, heure, new DureeEvenement(5), 10);
        Rappel e2 = new Rappel(new TitreEvenement("A"), alice, date, heure, new DureeEvenement(5), 10);
        assertNotEquals(e1.id(), e2.id());
    }

    @Test
    void rappel_dateFinCalculeeCorrectement() {
        Rappel e = new Rappel(new TitreEvenement("A"), alice, date, heure, new DureeEvenement(15), 10);
        assertEquals(dateTime.plusMinutes(15), e.dateFin());
    }
}
