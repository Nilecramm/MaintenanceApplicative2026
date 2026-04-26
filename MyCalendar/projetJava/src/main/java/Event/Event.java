package Event;

import domain.DureeEvenement;
import domain.EventId;
import domain.TitreEvenement;

import java.time.LocalDateTime;

public abstract class Event {

    private final EventId id;
    protected final TitreEvenement titre;
    protected final String proprietaire;
    protected final LocalDateTime dateDebut;
    protected final DureeEvenement duree;

    protected Event(TitreEvenement titre, String proprietaire, LocalDateTime dateDebut, DureeEvenement duree) {
        this.id = new EventId();
        this.titre = titre;
        this.proprietaire = proprietaire;
        this.dateDebut = dateDebut;
        this.duree = duree;
    }

    public EventId id() {
        return id;
    }

    public TitreEvenement titre() {
        return titre;
    }

    public String proprietaire() {
        return proprietaire;
    }

    public LocalDateTime dateDebut() {
        return dateDebut;
    }

    public DureeEvenement duree() {
        return duree;
    }

    public LocalDateTime dateFin() {
        return dateDebut.plusMinutes(duree.value());
    }

    public abstract String description();

    public abstract boolean estDansPeriode(LocalDateTime debut, LocalDateTime fin);
}
