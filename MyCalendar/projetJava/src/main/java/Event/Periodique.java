package Event;

import domain.DateEvenement;
import domain.DureeEvenement;
import domain.FrequenceRepetition;
import domain.HeureDebut;
import domain.Proprietaire;
import domain.TitreEvenement;

import java.time.LocalDateTime;

public class Periodique extends Event {

    private final FrequenceRepetition frequence;

    public Periodique(TitreEvenement titre, Proprietaire proprietaire, DateEvenement date, HeureDebut heureDebut, DureeEvenement duree,
                      FrequenceRepetition frequence) {
        super(titre, proprietaire, date, heureDebut, duree);
        this.frequence = frequence;
    }

    @Override
    public String description() {
        return "Événement périodique : " + titre + " tous les " + frequence + " jours";
    }

    @Override
    public boolean estDansPeriode(LocalDateTime debut, LocalDateTime fin) {
        LocalDateTime temp = dateDebut();
        while (temp.isBefore(fin)) {
            if (!temp.isBefore(debut)) {
                return true;
            }
            temp = temp.plusDays(frequence.value());
        }
        return false;
    }
}
