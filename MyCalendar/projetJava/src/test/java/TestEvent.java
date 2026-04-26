import Event.*;
import domain.*;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestEvent {

    private final DateEvenement date = new DateEvenement(LocalDate.of(2024, 6, 15));
    private final HeureDebut heure = new HeureDebut(LocalTime.of(10, 30));
    private final LocalDateTime dateTime = LocalDateTime.of(2024, 6, 15, 10, 30);
    private final Proprietaire alice = new Proprietaire("Alice");
    private final Proprietaire bob = new Proprietaire("Bob");
    private final Proprietaire system = new Proprietaire("System");

    @Test
    void description_rdvPersonnel() {
        RDVPerso e = new RDVPerso(
                new TitreEvenement("Médecin"),
                alice,
                date,
                heure,
                new DureeEvenement(30)
        );
        assertEquals("RDV : Médecin à " + dateTime, e.description());
    }

    @Test
    void description_reunion() {
        Reunion e = new Reunion(
                new TitreEvenement("Sprint Review"),
                bob,
                date,
                heure,
                new DureeEvenement(60),
                new LieuEvenement("Salle A"),
                new Participants("Alice", "Charlie")
        );
        assertEquals("Réunion : Sprint Review à Salle A avec Alice, Charlie", e.description());
    }

    @Test
    void description_periodique() {
        Periodique e = new Periodique(
                new TitreEvenement("Backup"),
                system,
                date,
                heure,
                new DureeEvenement(15),
                new FrequenceRepetition(7)
        );
        assertEquals("Événement périodique : Backup tous les 7 jours", e.description());
    }

    @Test
    void rdvPerso_idsSontUniques() {
        RDVPerso e1 = new RDVPerso(new TitreEvenement("A"), alice, date, heure, new DureeEvenement(30));
        RDVPerso e2 = new RDVPerso(new TitreEvenement("A"), alice, date, heure, new DureeEvenement(30));
        // Chaque event a un id différent
        org.junit.jupiter.api.Assertions.assertNotEquals(e1.id(), e2.id());
    }

    @Test
    void estDansPeriode_rdvDansLaPeriode() {
        RDVPerso e = new RDVPerso(new TitreEvenement("Médecin"), alice, date, heure, new DureeEvenement(30));
        org.junit.jupiter.api.Assertions.assertTrue(e.estDansPeriode(dateTime.minusHours(1), dateTime.plusHours(1)));
    }

    @Test
    void estDansPeriode_rdvHorsPeriode() {
        RDVPerso e = new RDVPerso(new TitreEvenement("Médecin"), alice, date, heure, new DureeEvenement(30));
        org.junit.jupiter.api.Assertions.assertFalse(e.estDansPeriode(dateTime.plusDays(1), dateTime.plusDays(2)));
    }
}
