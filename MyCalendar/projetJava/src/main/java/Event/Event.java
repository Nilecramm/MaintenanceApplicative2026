package Event;

import domain.DateEvenement;
import domain.DureeEvenement;
import domain.EventId;
import domain.HeureDebut;
import domain.Proprietaire;
import domain.TitreEvenement;

import java.time.LocalDateTime;

public abstract class Event {

    private final EventId id;
    protected final TitreEvenement titre;
    protected final Proprietaire proprietaire;
    protected final DateEvenement date;
    protected final HeureDebut heureDebut;
    protected final DureeEvenement duree;

    protected Event(TitreEvenement titre, Proprietaire proprietaire, DateEvenement date, HeureDebut heureDebut, DureeEvenement duree) {
        this.id = new EventId();
        this.titre = titre;
        this.proprietaire = proprietaire;
        this.date = date;
        this.heureDebut = heureDebut;
        this.duree = duree;
    }

    public EventId id() {
        return id;
    }

    public TitreEvenement titre() {
        return titre;
    }

    public Proprietaire proprietaire() {
        return proprietaire;
    }

    public LocalDateTime dateDebut() {
        return LocalDateTime.of(date.value(), heureDebut.value());
    }

    public final DateEvenement date() {
        return date;
    }

    public final HeureDebut heureDebut() {
        return heureDebut;
    }

    public DureeEvenement duree() {
        return duree;
    }

    public LocalDateTime dateFin() {
        return dateDebut().plusMinutes(duree.value());
    }

    public abstract String description();

    public abstract boolean estDansPeriode(LocalDateTime debut, LocalDateTime fin);
}
