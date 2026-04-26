import Event.*;
import domain.*;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestEvent {

    private final LocalDateTime date = LocalDateTime.of(2024, 6, 15, 10, 30);

    @Test
    void description_rdvPersonnel() {
        RDVPerso e = new RDVPerso(
                new TitreEvenement("Médecin"),
                "Alice",
                date,
                new DureeEvenement(30)
        );
        assertEquals("RDV : Médecin à " + date, e.description());
    }

    @Test
    void description_reunion() {
        Reunion e = new Reunion(
                new TitreEvenement("Sprint Review"),
                "Bob",
                date,
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
                "System",
                date,
                new DureeEvenement(15),
                new FrequenceRepetition(7)
        );
        assertEquals("Événement périodique : Backup tous les 7 jours", e.description());
    }

    @Test
    void rdvPerso_idsSontUniques() {
        RDVPerso e1 = new RDVPerso(new TitreEvenement("A"), "Alice", date, new DureeEvenement(30));
        RDVPerso e2 = new RDVPerso(new TitreEvenement("A"), "Alice", date, new DureeEvenement(30));
        // Chaque event a un id différent
        org.junit.jupiter.api.Assertions.assertNotEquals(e1.id(), e2.id());
    }

    @Test
    void estDansPeriode_rdvDansLaPeriode() {
        RDVPerso e = new RDVPerso(new TitreEvenement("Médecin"), "Alice", date, new DureeEvenement(30));
        org.junit.jupiter.api.Assertions.assertTrue(e.estDansPeriode(date.minusHours(1), date.plusHours(1)));
    }

    @Test
    void estDansPeriode_rdvHorsPeriode() {
        RDVPerso e = new RDVPerso(new TitreEvenement("Médecin"), "Alice", date, new DureeEvenement(30));
        org.junit.jupiter.api.Assertions.assertFalse(e.estDansPeriode(date.plusDays(1), date.plusDays(2)));
    }
}
