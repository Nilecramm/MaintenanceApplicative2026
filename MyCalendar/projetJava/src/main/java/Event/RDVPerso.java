package Event;

import domain.DureeEvenement;
import domain.Proprietaire;
import domain.TitreEvenement;

import java.time.LocalDateTime;

public class RDVPerso extends Event {

    public RDVPerso(TitreEvenement titre, Proprietaire proprietaire, LocalDateTime dateDebut, DureeEvenement duree) {
        super(titre, proprietaire, dateDebut, duree);
    }

    @Override
    public String description() {
        return "RDV : " + titre + " à " + dateDebut;
    }

    @Override
    public boolean estDansPeriode(LocalDateTime debut, LocalDateTime fin) {
        return !dateDebut.isBefore(debut) && !dateDebut.isAfter(fin);
    }
}
