import java.time.LocalDateTime;

public class RDVPerso extends Event{
    RDVPerso(String title, String proprietaire, LocalDateTime dateDebut, int dureeMinutes, String lieu, String participants, int frequenceJours){
        super("RDV_PERSONNEL", title, proprietaire, dateDebut, dureeMinutes, lieu, participants, frequenceJours);
    }

    @Override
    public String description(){
        return "RDV : " + title + " à " + dateDebut.toString();
    }
}
