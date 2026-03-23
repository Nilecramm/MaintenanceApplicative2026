package Event;

import java.time.LocalDateTime;

public class EventCustom extends Event {
    public EventCustom(String type, String title, String proprietaire, LocalDateTime dateDebut, int dureeMinutes, String lieu, String participants, int frequenceJours){
        super(type, title, proprietaire, dateDebut, dureeMinutes, lieu, participants, frequenceJours);
    }

    @Override
    public String description() {
        return "";
    }
}
