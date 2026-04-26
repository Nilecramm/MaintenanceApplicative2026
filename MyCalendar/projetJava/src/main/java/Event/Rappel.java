package Event;

import domain.DateEvenement;
import domain.DureeEvenement;
import domain.HeureDebut;
import domain.Proprietaire;
import domain.TitreEvenement;

import java.time.LocalDateTime;

public class Rappel extends Event {

    private final int minutesAvant;

    public Rappel(TitreEvenement titre, Proprietaire proprietaire, DateEvenement date, HeureDebut heureDebut, DureeEvenement duree, int minutesAvant) {
        super(titre, proprietaire, date, heureDebut, duree);
        this.minutesAvant = minutesAvant;
    }

    @Override
    public String description() {
        return "Rappel : " + titre + " dans " + minutesAvant + " minutes";
    }

    @Override
    public boolean estDansPeriode(LocalDateTime debut, LocalDateTime fin) {
        return !dateDebut().isBefore(debut) && !dateDebut().isAfter(fin);
    }
}
