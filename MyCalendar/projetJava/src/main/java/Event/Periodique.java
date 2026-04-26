package Event;

import domain.DateEvenement;
import domain.DureeEvenement;
import domain.FrequenceRepetition;
import domain.HeureDebut;
import domain.Proprietaire;
import domain.TitreEvenement;

import java.time.LocalDateTime;
import java.util.stream.Stream;

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
        return Stream.iterate(dateDebut(), t -> t.isBefore(fin), t -> t.plusDays(frequence.value()))
                .anyMatch(t -> !t.isBefore(debut));
    }
}
