import java.time.LocalDateTime;

public class Periodique extends Event {
    public Periodique(String title, String proprietaire, LocalDateTime dateDebut, int dureeMinutes, String lieu, String participants, int frequenceJours) {
        super("PERIODIQUE", title, proprietaire, dateDebut, dureeMinutes, lieu, participants, frequenceJours);
    }

    @Override
    public String description() {
        return "Événement périodique : " + title + " tous les " + frequenceJours + " jours";
    }

}
