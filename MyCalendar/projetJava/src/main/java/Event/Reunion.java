package Event;

import java.time.LocalDateTime;

public class Reunion extends Event {

    public Reunion (String title, String proprietaire, LocalDateTime dateDebut, int dureeMinutes, String lieu, String participants, int frequenceJours){
        super("REUNION", title, proprietaire, dateDebut, dureeMinutes, lieu, participants, frequenceJours);
    }

    @Override
    public String description(){
        return "Réunion : " + title + " à " + lieu + " avec " + participants;
    }
}
