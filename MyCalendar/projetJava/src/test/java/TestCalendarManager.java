import Event.*;
import domain.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class TestCalendarManager {

    private CalendarManager manager;
    private final LocalDateTime base = LocalDateTime.of(2024, 6, 15, 10, 0);
    private final Proprietaire alice = new Proprietaire("Alice");
    private final Proprietaire bob = new Proprietaire("Bob");
    private final Proprietaire system = new Proprietaire("System");

    @BeforeEach
    void setUp() {
        manager = new CalendarManager();
    }

    @Test
    void ajouterEvent_ajouteBienLEvent() {
        RDVPerso rdv = new RDVPerso(new TitreEvenement("Médecin"), alice, base, new DureeEvenement(30));
        manager.ajouterEvent(rdv);
        assertEquals(1, manager.tousLesEvents().size());
        assertEquals("Médecin", manager.tousLesEvents().get(0).titre().value());
    }

    @Test
    void ajouterEvent_plusieursEvents() {
        manager.ajouterEvent(new RDVPerso(new TitreEvenement("Médecin"), alice, base, new DureeEvenement(30)));
        manager.ajouterEvent(new Reunion(
                new TitreEvenement("Sprint"), bob, base.plusHours(2), new DureeEvenement(60),
                new LieuEvenement("Salle A"), new Participants("Alice")
        ));
        assertEquals(2, manager.tousLesEvents().size());
    }

    @Test
    void eventsDansPeriode_retourneEventDansLaPeriode() {
        manager.ajouterEvent(new RDVPerso(new TitreEvenement("Médecin"), alice, base, new DureeEvenement(30)));
        List<Event> result = manager.eventsDansPeriode(base.minusHours(1), base.plusHours(1));
        assertEquals(1, result.size());
    }

    @Test
    void eventsDansPeriode_excluEventHorsPeriode() {
        manager.ajouterEvent(new RDVPerso(new TitreEvenement("Médecin"), alice, base, new DureeEvenement(30)));
        List<Event> result = manager.eventsDansPeriode(base.plusDays(1), base.plusDays(2));
        assertTrue(result.isEmpty());
    }

    @Test
    void eventsDansPeriode_inclutEventEnBorneDebut() {
        manager.ajouterEvent(new RDVPerso(new TitreEvenement("Médecin"), alice, base, new DureeEvenement(30)));
        List<Event> result = manager.eventsDansPeriode(base, base.plusHours(1));
        assertEquals(1, result.size());
    }

    @Test
    void eventsDansPeriode_inclutEventEnBorneFin() {
        manager.ajouterEvent(new RDVPerso(new TitreEvenement("Médecin"), alice, base, new DureeEvenement(30)));
        List<Event> result = manager.eventsDansPeriode(base.minusHours(1), base);
        assertEquals(1, result.size());
    }

    @Test
    void eventsDansPeriode_periodiqueAtteintLaPeriode() {
        manager.ajouterEvent(new Periodique(
                new TitreEvenement("Backup"), system, base.minusDays(7), new DureeEvenement(15),
                new FrequenceRepetition(7)
        ));
        List<Event> result = manager.eventsDansPeriode(base.minusHours(1), base.plusHours(1));
        assertEquals(1, result.size());
    }

    @Test
    void eventsDansPeriode_periodiqueNAtteintPasLaPeriode() {
        manager.ajouterEvent(new Periodique(
                new TitreEvenement("Backup"), system, base.minusDays(10), new DureeEvenement(15),
                new FrequenceRepetition(30)
        ));
        List<Event> result = manager.eventsDansPeriode(base.minusHours(1), base.plusHours(1));
        assertTrue(result.isEmpty());
    }

    @Test
    void conflit_deuxEventsQuiSeChevauchent() {
        Event e1 = new RDVPerso(new TitreEvenement("A"), alice, base, new DureeEvenement(60));
        Event e2 = new RDVPerso(new TitreEvenement("B"), alice, base.plusMinutes(30), new DureeEvenement(60));
        assertTrue(manager.conflit(e1, e2));
    }

    @Test
    void conflit_deuxEventsSansChevauchement() {
        Event e1 = new RDVPerso(new TitreEvenement("A"), alice, base, new DureeEvenement(60));
        Event e2 = new RDVPerso(new TitreEvenement("B"), alice, base.plusHours(2), new DureeEvenement(60));
        assertFalse(manager.conflit(e1, e2));
    }

    @Test
    void conflit_deuxEventsConsecutifsSansChevauchement() {
        Event e1 = new RDVPerso(new TitreEvenement("A"), alice, base, new DureeEvenement(60));
        Event e2 = new RDVPerso(new TitreEvenement("B"), alice, base.plusHours(1), new DureeEvenement(60));
        assertFalse(manager.conflit(e1, e2));
    }

    @Test
    void conflit_avecPeriodiqueRetourneChevauchementHoraire() {
        Event e1 = new Periodique(
                new TitreEvenement("Backup"), system, base, new DureeEvenement(30),
                new FrequenceRepetition(7)
        );
        Event e2 = new RDVPerso(new TitreEvenement("A"), alice, base, new DureeEvenement(60));
        // Le conflit se base sur le chevauchement horaire, pas sur le type
        assertTrue(manager.conflit(e1, e2));
        assertTrue(manager.conflit(e2, e1));
    }

    @Test
    void eventsDansPeriode_listeVideRetourneListeVide() {
        List<Event> result = manager.eventsDansPeriode(base, base.plusHours(1));
        assertTrue(result.isEmpty());
    }

    @Test
    void conflit_e1ApresE2_pasDeChevauchement() {
        Event e1 = new RDVPerso(new TitreEvenement("A"), alice, base.plusHours(3), new DureeEvenement(60));
        Event e2 = new RDVPerso(new TitreEvenement("B"), alice, base, new DureeEvenement(60));
        assertFalse(manager.conflit(e1, e2));
    }

    @Test
    void afficherEvenements_neLeveAucuneException() {
        manager.ajouterEvent(new RDVPerso(new TitreEvenement("Médecin"), alice, base, new DureeEvenement(30)));
        manager.ajouterEvent(new Reunion(
                new TitreEvenement("Sprint"), bob, base.plusHours(2), new DureeEvenement(60),
                new LieuEvenement("Salle A"), new Participants("Alice")
        ));
        assertDoesNotThrow(() -> manager.afficherEvenements());
    }

    @Test
    void eventsDansPeriode_excluEventApresLaPeriode() {
        manager.ajouterEvent(new RDVPerso(new TitreEvenement("Médecin"), alice, base.plusDays(5), new DureeEvenement(30)));
        List<Event> result = manager.eventsDansPeriode(base, base.plusDays(1));
        assertTrue(result.isEmpty());
    }

    @Test
    void supprimerEvent_supprimeParId() {
        RDVPerso rdv = new RDVPerso(new TitreEvenement("Médecin"), alice, base, new DureeEvenement(30));
        manager.ajouterEvent(rdv);
        assertEquals(1, manager.tousLesEvents().size());

        manager.supprimerEvent(rdv.id());
        assertEquals(0, manager.tousLesEvents().size());
    }

    @Test
    void supprimerEvent_idInexistantNeFaitRien() {
        manager.ajouterEvent(new RDVPerso(new TitreEvenement("Médecin"), alice, base, new DureeEvenement(30)));
        manager.supprimerEvent(new EventId());
        assertEquals(1, manager.tousLesEvents().size());
    }

    @Test
    void eventsDansPeriode_melangeDeTypesEvenements() {
        manager.ajouterEvent(new RDVPerso(new TitreEvenement("Médecin"), alice, base, new DureeEvenement(30)));
        manager.ajouterEvent(new Reunion(
                new TitreEvenement("Sprint"), bob, base.plusHours(1), new DureeEvenement(60),
                new LieuEvenement("Salle A"), new Participants("Alice")
        ));
        manager.ajouterEvent(new Periodique(
                new TitreEvenement("Backup"), system, base.minusDays(7), new DureeEvenement(15),
                new FrequenceRepetition(7)
        ));
        manager.ajouterEvent(new Rappel(
                new TitreEvenement("Médicaments"), alice, base.plusHours(2), new DureeEvenement(5), 10
        ));

        List<Event> result = manager.eventsDansPeriode(base.minusHours(1), base.plusHours(3));
        assertEquals(4, result.size());
    }

    @Test
    void conflit_reunionAvecRDVPerso() {
        Event reunion = new Reunion(
                new TitreEvenement("Sprint"), bob, base, new DureeEvenement(60),
                new LieuEvenement("Salle A"), new Participants("Alice")
        );
        Event rdv = new RDVPerso(new TitreEvenement("Médecin"), alice, base.plusMinutes(30), new DureeEvenement(30));
        assertTrue(manager.conflit(reunion, rdv));
    }

    @Test
    void conflit_rappelAvecRDVPerso() {
        Event rappel = new Rappel(new TitreEvenement("Médicaments"), alice, base, new DureeEvenement(5), 10);
        Event rdv = new RDVPerso(new TitreEvenement("Médecin"), alice, base.plusMinutes(2), new DureeEvenement(30));
        assertTrue(manager.conflit(rappel, rdv));
    }

    @Test
    void afficherEvenements_avecRappelNeLevePasException() {
        manager.ajouterEvent(new Rappel(
                new TitreEvenement("Médicaments"), alice, base, new DureeEvenement(5), 10
        ));
        assertDoesNotThrow(() -> manager.afficherEvenements());
    }

    @Test
    void eventsDansPeriode_rappelDansLaPeriode() {
        manager.ajouterEvent(new Rappel(
                new TitreEvenement("Médicaments"), alice, base, new DureeEvenement(5), 10
        ));
        List<Event> result = manager.eventsDansPeriode(base.minusHours(1), base.plusHours(1));
        assertEquals(1, result.size());
    }

    @Test
    void tousLesEvents_retourneCopieDefensive() {
        manager.ajouterEvent(new RDVPerso(new TitreEvenement("Médecin"), alice, base, new DureeEvenement(30)));
        List<Event> copie = manager.tousLesEvents();
        copie.clear();
        assertEquals(1, manager.tousLesEvents().size());
    }
}
