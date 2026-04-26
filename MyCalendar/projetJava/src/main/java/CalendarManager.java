import Event.Event;
import domain.EventId;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class CalendarManager {

    private final List<Event> events;

    public CalendarManager() {
        this.events = new ArrayList<>();
    }

    public void ajouterEvent(Event event) {
        events.add(event);
    }

    public List<Event> eventsDansPeriode(LocalDateTime debut, LocalDateTime fin) {
        return events.stream()
                .filter(e -> e.estDansPeriode(debut, fin))
                .collect(Collectors.toList());
    }

    public boolean conflit(Event e1, Event e2) {
        return e1.dateDebut().isBefore(e2.dateFin()) && e1.dateFin().isAfter(e2.dateDebut());
    }

    public void afficherEvenements() {
        for (Event e : events) {
            System.out.println(e.description());
        }
    }

    public void supprimerEvent(EventId id) {
        events.removeIf(e -> e.id().equals(id));
    }

    public List<Event> tousLesEvents() {
        return new ArrayList<>(events);
    }
}
