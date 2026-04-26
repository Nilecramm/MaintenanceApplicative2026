package Event;

import domain.DateEvenement;
import domain.DelaiRappel;
import domain.DureeEvenement;
import domain.HeureDebut;
import domain.Proprietaire;
import domain.TitreEvenement;

import java.time.LocalDateTime;

public class Rappel extends Event {

    private final DelaiRappel delai;

    public Rappel(TitreEvenement titre, Proprietaire proprietaire, DateEvenement date, HeureDebut heureDebut, DureeEvenement duree, DelaiRappel delai) {
        super(titre, proprietaire, date, heureDebut, duree);
        this.delai = delai;
    }

    @Override
    public String description() {
        return "Rappel : " + titre + " dans " + delai + " minutes";
    }

    @Override
    public boolean estDansPeriode(LocalDateTime debut, LocalDateTime fin) {
        return !dateDebut().isBefore(debut) && !dateDebut().isAfter(fin);
    }
}
