package Event;

import domain.DateEvenement;
import domain.DureeEvenement;
import domain.HeureDebut;
import domain.LieuEvenement;
import domain.Participants;
import domain.Proprietaire;
import domain.TitreEvenement;

import java.time.LocalDateTime;

public class Reunion extends Event {

    private final LieuEvenement lieu;
    private final Participants participants;

    public Reunion(TitreEvenement titre, Proprietaire proprietaire, DateEvenement date, HeureDebut heureDebut, DureeEvenement duree,
                   LieuEvenement lieu, Participants participants) {
        super(titre, proprietaire, date, heureDebut, duree);
        this.lieu = lieu;
        this.participants = participants;
    }

    @Override
    public String description() {
        return "Réunion : " + titre + " à " + lieu + " avec " + participants;
    }

    @Override
    public boolean estDansPeriode(LocalDateTime debut, LocalDateTime fin) {
        return !dateDebut().isBefore(debut) && !dateDebut().isAfter(fin);
    }
}
