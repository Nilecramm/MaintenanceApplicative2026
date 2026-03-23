import Event.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.time.LocalDateTime;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

class TestCalendarManager {

    private CalendarManager manager;
    private final LocalDateTime base = LocalDateTime.of(2024, 6, 15, 10, 0);

    @BeforeEach
    void setUp() {
        manager = new CalendarManager();
    }

    @Test
    void ajouterEvent_ajouteBienLEvent() {
        manager.ajouterEvent("RDV_PERSONNEL", "Médecin", "Alice", base, 30, null, null, 0);
        assertEquals(1, manager.events.size());
        assertEquals("Médecin", manager.events.get(0).title);
    }

    @Test
    void ajouterEvent_plusieursEvents() {
        manager.ajouterEvent("RDV_PERSONNEL", "Médecin", "Alice", base, 30, null, null, 0);
        manager.ajouterEvent("REUNION", "Sprint", "Bob", base.plusHours(2), 60, "Salle A", "Alice", 0);
        assertEquals(2, manager.events.size());
    }

    @Test
    void eventsDansPeriode_retourneEventDansLaPeriode() {
        manager.ajouterEvent("RDV_PERSONNEL", "Médecin", "Alice", base, 30, null, null, 0);
        List<Event> result = manager.eventsDansPeriode(base.minusHours(1), base.plusHours(1));
        assertEquals(1, result.size());
    }

    @Test
    void eventsDansPeriode_excluEventHorsPeriode() {
        manager.ajouterEvent("RDV_PERSONNEL", "Médecin", "Alice", base, 30, null, null, 0);
        List<Event> result = manager.eventsDansPeriode(base.plusDays(1), base.plusDays(2));
        assertTrue(result.isEmpty());
    }

    @Test
    void eventsDansPeriode_inclutEventEnBorneDebut() {
        manager.ajouterEvent("RDV_PERSONNEL", "Médecin", "Alice", base, 30, null, null, 0);
        List<Event> result = manager.eventsDansPeriode(base, base.plusHours(1));
        assertEquals(1, result.size());
    }

    @Test
    void eventsDansPeriode_inclutEventEnBorneFin() {
        manager.ajouterEvent("RDV_PERSONNEL", "Médecin", "Alice", base, 30, null, null, 0);
        List<Event> result = manager.eventsDansPeriode(base.minusHours(1), base);
        assertEquals(1, result.size());
    }

    @Test
    void eventsDansPeriode_periodiqueAtteintLaPeriode() {
        // Périodique tous les 7 jours, démarre 7 jours avant → tombe dans la période
        manager.ajouterEvent("PERIODIQUE", "Backup", "System", base.minusDays(7), 15, null, null, 7);
        List<Event> result = manager.eventsDansPeriode(base.minusHours(1), base.plusHours(1));
        assertEquals(1, result.size());
    }

    @Test
    void eventsDansPeriode_periodiqueNAtteintPasLaPeriode() {
        // Périodique tous les 30 jours, ne tombe jamais dans la fenêtre d'1h
        manager.ajouterEvent("PERIODIQUE", "Backup", "System", base.minusDays(10), 15, null, null, 30);
        List<Event> result = manager.eventsDansPeriode(base.minusHours(1), base.plusHours(1));
        assertTrue(result.isEmpty());
    }

    @Test
    void conflit_deuxEventsQuiSeChevauchent() {
        Event e1 = new Event("RDV_PERSONNEL", "A", "Alice", base, 60, null, null, 0);
        Event e2 = new Event("RDV_PERSONNEL", "B", "Alice", base.plusMinutes(30), 60, null, null, 0);
        assertTrue(manager.conflit(e1, e2));
    }

    @Test
    void conflit_deuxEventsSansChevauchement() {
        Event e1 = new Event("RDV_PERSONNEL", "A", "Alice", base, 60, null, null, 0);
        Event e2 = new Event("RDV_PERSONNEL", "B", "Alice", base.plusHours(2), 60, null, null, 0);
        assertFalse(manager.conflit(e1, e2));
    }

    @Test
    void conflit_deuxEventsConsecutifsSansChevauchement() {
        Event e1 = new Event("RDV_PERSONNEL", "A", "Alice", base, 60, null, null, 0);
        Event e2 = new Event("RDV_PERSONNEL", "B", "Alice", base.plusHours(1), 60, null, null, 0);
        assertFalse(manager.conflit(e1, e2));
    }

    @Test
    void conflit_avecPeriodiqueRetourneFalse() {
        Event e1 = new Event("PERIODIQUE", "Backup", "System", base, 30, null, null, 7);
        Event e2 = new Event("RDV_PERSONNEL", "A", "Alice", base, 60, null, null, 0);
        assertFalse(manager.conflit(e1, e2));
        assertFalse(manager.conflit(e2, e1));
    }

    @Test
    void eventsDansPeriode_listeVideRetourneListeVide() {
        List<Event> result = manager.eventsDansPeriode(base, base.plusHours(1));
        assertTrue(result.isEmpty());
    }

    @Test
    void conflit_e1ApresE2_pasDeChevauchement() {
        Event e1 = new Event("RDV_PERSONNEL", "A", "Alice", base.plusHours(3), 60, null, null, 0);
        Event e2 = new Event("RDV_PERSONNEL", "B", "Alice", base, 60, null, null, 0);
        assertFalse(manager.conflit(e1, e2));
    }

    @Test
    void afficherEvenements_neLeveAucuneException() {
        manager.ajouterEvent("RDV_PERSONNEL", "Médecin", "Alice", base, 30, null, null, 0);
        manager.ajouterEvent("REUNION", "Sprint", "Bob", base.plusHours(2), 60, "Salle A", "Alice", 0);
        assertDoesNotThrow(() -> manager.afficherEvenements());
    }

    @Test
    void eventsDansPeriode_excluEventApresLaPeriode() {
        // dateDebut n'est pas before(debut) ✅, mais isAfter(fin) ✅ → doit être exclu
        manager.ajouterEvent("RDV_PERSONNEL", "Médecin", "Alice", base.plusDays(5), 30, null, null, 0);
        List<Event> result = manager.eventsDansPeriode(base, base.plusDays(1));
        assertTrue(result.isEmpty());
    }
}