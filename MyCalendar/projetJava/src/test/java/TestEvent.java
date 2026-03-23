import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.Assert.assertEquals;

public class TestEvent {

    private final LocalDateTime date = LocalDateTime.of(2024, 6, 15, 10, 30);

    @Test
    void description_rdvPersonnel() {
        RDVPerso e = new RDVPerso("Médecin", "Alice", date, 30, null, null, 0);
        assertEquals("RDV : Médecin à " + date, e.description());
    }

    @Test
    void description_reunion() {
        Reunion e = new Reunion("Sprint Review", "Bob", date, 60, "Salle A", "Alice,Charlie", 0);
        assertEquals("Réunion : Sprint Review à Salle A avec Alice,Charlie", e.description());
    }

    @Test
    void description_periodique() {
        Periodique e = new Periodique("Backup", "System", date, 15, null, null, 7);
        assertEquals("Événement périodique : Backup tous les 7 jours", e.description());
    }

    @Test
    void description_typeInconnu_retourneChainVide() {
        Event e = new Event("INCONNU", "Test", "Alice", date, 10, null, null, 0);
        assertEquals("", e.description());
    }

    @Test
    void constructeur_affecteCorrectementLesChamps() {
        Event e = new Event("INCONNU", "Kick-off", "Alice", date, 90, "Salle B", "Bob,Carol", 0);
        assertEquals("INCONNU", e.type);
        assertEquals("Kick-off", e.title);
        assertEquals("Alice", e.proprietaire);
        assertEquals(date, e.dateDebut);
        assertEquals(90, e.dureeMinutes);
        assertEquals("Salle B", e.lieu);
        assertEquals("Bob,Carol", e.participants);
        assertEquals(0, e.frequenceJours);
    }
}
