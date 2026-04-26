package Event;

import domain.DureeEvenement;
import domain.LieuEvenement;
import domain.Participants;
import domain.TitreEvenement;

import java.time.LocalDateTime;

public class Reunion extends Event {

    private final LieuEvenement lieu;
    private final Participants participants;

    public Reunion(TitreEvenement titre, String proprietaire, LocalDateTime dateDebut, DureeEvenement duree,
                   LieuEvenement lieu, Participants participants) {
        super(titre, proprietaire, dateDebut, duree);
        this.lieu = lieu;
        this.participants = participants;
    }

    @Override
    public String description() {
        return "Réunion : " + titre + " à " + lieu + " avec " + participants;
    }

    @Override
    public boolean estDansPeriode(LocalDateTime debut, LocalDateTime fin) {
        return !dateDebut.isBefore(debut) && !dateDebut.isAfter(fin);
    }
}
