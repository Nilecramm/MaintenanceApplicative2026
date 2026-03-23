package Event;

import java.time.LocalDateTime;

public class RDVPerso extends Event {
    public RDVPerso(String title, String proprietaire, LocalDateTime dateDebut, int dureeMinutes, String lieu, int frequenceJours){
        super("RDV_PERSONNEL", title, proprietaire, dateDebut, dureeMinutes, lieu, null, frequenceJours);
    }

    @Override
    public String description(){
        return "RDV : " + title + " à " + dateDebut.toString();
    }
}
