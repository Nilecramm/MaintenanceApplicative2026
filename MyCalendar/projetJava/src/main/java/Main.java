import Event.*;
import domain.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.WeekFields;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Scanner;

public class Main {

    private static final Map<String, String> CREDENTIALS = Map.of(
            "Roger", "Chat",
            "Pierre", "KiRouhl"
    );

    private static CalendarManager calendar;
    private static Scanner scanner;
    private static String utilisateur;
    private static boolean continuer = true;
    private static String[] utilisateurs = new String[99];
    private static String[] motsDePasses = new String[99];
    private static int nbUtilisateurs;

    public static void main(String[] args) {
        calendar = new CalendarManager();
        scanner = new Scanner(System.in);
        utilisateur = null;

        while (true) {

            while (utilisateur == null) {
                afficherBanniere();
                System.out.println("1 - Se connecter");
                System.out.println("2 - Créer un compte");
                System.out.println("Choix : ");

                Map<String, Runnable> authActions = Map.of(
                        "1", Main::connecter,
                        "2", Main::creerCompte
                );

                authActions.getOrDefault(scanner.nextLine(), () -> {}).run();
            }

            while (continuer && utilisateur != null) {
                System.out.println("\nBonjour, " + utilisateur);
                System.out.println("=== Menu Gestionnaire d'Événements ===");
                System.out.println("1 - Voir les événements");
                System.out.println("2 - Ajouter un rendez-vous perso");
                System.out.println("3 - Ajouter une réunion");
                System.out.println("4 - Ajouter un évènement périodique");
                System.out.println("5 - Se déconnecter");
                System.out.print("Votre choix : ");

                String choix = scanner.nextLine();

                Map<String, Runnable> mainMenu = Map.of(
                        "1", Main::afficherSousMenu,
                        "2", Main::ajouterRDV,
                        "3", Main::ajouterReunion,
                        "4", Main::ajouterPeriodique,
                        "5", Main::deconnecter
                );

                mainMenu.getOrDefault(choix, Main::deconnecter).run();
            }
        }
    }

    private static void afficherBanniere() {
        System.out.println("  _____         _                   _                __  __");
        System.out.println(" / ____|       | |                 | |              |  \\/  |");
        System.out.println("| |       __ _ | |  ___  _ __    __| |  __ _  _ __  | \\  / |  __ _  _ __    __ _   __ _   ___  _ __");
        System.out.println("| |      / _` || | / _ \\| '_ \\  / _` | / _` || '__| | |\\/| | / _` || '_ \\  / _` | / _` | / _ \\| '__|");
        System.out.println("| |____ | (_| || ||  __/| | | || (_| || (_| || |    | |  | || (_| || | | || (_| || (_| ||  __/| |");
        System.out.println(" \\_____| \\__,_||_| \\___||_| |_| \\__,_| \\__,_||_|    |_|  |_| \\__,_||_| |_| \\__,_| \\__, | \\___||_|");
        System.out.println("                                                                                   __/ |");
        System.out.println("                                                                                  |___/");
    }

    private static void connecter() {
        System.out.print("Nom d'utilisateur: ");
        String nom = scanner.nextLine();
        String mdp = CREDENTIALS.get(nom);
        boolean ok = mdp != null && verifierMotDePasse(mdp);
        for (int i = 0; !ok && i < nbUtilisateurs; i++) {
            ok = utilisateurs[i].equals(nom) && verifierMotDePasse(motsDePasses[i]);
        }
        utilisateur = ok ? nom : null;
    }

    private static void creerCompte() {
        System.out.print("Nom d'utilisateur: ");
        String nom = scanner.nextLine();
        System.out.print("Mot de passe: ");
        String mdp1 = scanner.nextLine();
        System.out.print("Répéter mot de passe: ");
        String mdp2 = scanner.nextLine();
        boolean ok = mdp1.equals(mdp2);
        if (ok) {
            utilisateurs[nbUtilisateurs] = nom;
            motsDePasses[nbUtilisateurs] = mdp1;
            nbUtilisateurs++;
            utilisateur = nom;
        }
        if (!ok) {
            System.out.println("Les mots de passes ne correspondent pas...");
        }
    }

    private static void deconnecter() {
        System.out.println("Déconnexion ! Voulez-vous continuer ? (O/N)");
        continuer = scanner.nextLine().trim().equalsIgnoreCase("oui");
        utilisateur = null;
    }

    private static void afficherSousMenu() {
        System.out.println("\n=== Menu de visualisation d'Événements ===");
        System.out.println("1 - Afficher TOUS les événements");
        System.out.println("2 - Afficher les événements d'un MOIS précis");
        System.out.println("3 - Afficher les événements d'une SEMAINE précise");
        System.out.println("4 - Afficher les événements d'un JOUR précis");
        System.out.println("5 - Retour");
        System.out.print("Votre choix : ");

        String choix = scanner.nextLine();

        Map<String, Runnable> sousMenu = Map.of(
                "1", calendar::afficherEvenements,
                "2", Main::afficherMois,
                "3", Main::afficherSemaine,
                "4", Main::afficherJour
        );

        sousMenu.getOrDefault(choix, () -> {}).run();
    }

    private static void afficherMois() {
        System.out.print("Entrez l'année (AAAA) : ");
        int anneeMois = Integer.parseInt(scanner.nextLine());
        System.out.print("Entrez le mois (1-12) : ");
        int mois = Integer.parseInt(scanner.nextLine());
        LocalDateTime debutMois = LocalDateTime.of(anneeMois, mois, 1, 0, 0);
        LocalDateTime finMois = debutMois.plusMonths(1).minusSeconds(1);
        afficherListe(calendar.eventsDansPeriode(debutMois, finMois));
    }

    private static void afficherSemaine() {
        System.out.print("Entrez l'année (AAAA) : ");
        int anneeSemaine = Integer.parseInt(scanner.nextLine());
        System.out.print("Entrez le numéro de semaine (1-52) : ");
        int semaine = Integer.parseInt(scanner.nextLine());
        LocalDateTime debutSemaine = LocalDateTime.now()
                .withYear(anneeSemaine)
                .with(WeekFields.of(Locale.FRANCE).weekOfYear(), semaine)
                .with(WeekFields.of(Locale.FRANCE).dayOfWeek(), 1)
                .withHour(0).withMinute(0);
        LocalDateTime finSemaine = debutSemaine.plusDays(7).minusSeconds(1);
        afficherListe(calendar.eventsDansPeriode(debutSemaine, finSemaine));
    }

    private static void afficherJour() {
        System.out.print("Entrez l'année (AAAA) : ");
        int anneeJour = Integer.parseInt(scanner.nextLine());
        System.out.print("Entrez le mois (1-12) : ");
        int moisJour = Integer.parseInt(scanner.nextLine());
        System.out.print("Entrez le jour (1-31) : ");
        int jour = Integer.parseInt(scanner.nextLine());
        LocalDateTime debutJour = LocalDateTime.of(anneeJour, moisJour, jour, 0, 0);
        LocalDateTime finJour = debutJour.plusDays(1).minusSeconds(1);
        afficherListe(calendar.eventsDansPeriode(debutJour, finJour));
    }

    private static void ajouterRDV() {
        System.out.print("Titre de l'événement : ");
        String titre = scanner.nextLine();
        System.out.print("Année (AAAA) : ");
        int annee = Integer.parseInt(scanner.nextLine());
        System.out.print("Mois (1-12) : ");
        int mois = Integer.parseInt(scanner.nextLine());
        System.out.print("Jour (1-31) : ");
        int jour = Integer.parseInt(scanner.nextLine());
        System.out.print("Heure début (0-23) : ");
        int heure = Integer.parseInt(scanner.nextLine());
        System.out.print("Minute début (0-59) : ");
        int minute = Integer.parseInt(scanner.nextLine());
        System.out.print("Durée (en minutes) : ");
        int duree = Integer.parseInt(scanner.nextLine());

        calendar.ajouterEvent(new RDVPerso(
                new TitreEvenement(titre), new Proprietaire(utilisateur),
                new DateEvenement(LocalDate.of(annee, mois, jour)),
                new HeureDebut(LocalTime.of(heure, minute)),
                new DureeEvenement(duree)
        ));
        System.out.println("Événement ajouté.");
    }

    private static void ajouterReunion() {
        System.out.print("Titre de l'événement : ");
        String titre = scanner.nextLine();
        System.out.print("Année (AAAA) : ");
        int annee = Integer.parseInt(scanner.nextLine());
        System.out.print("Mois (1-12) : ");
        int mois = Integer.parseInt(scanner.nextLine());
        System.out.print("Jour (1-31) : ");
        int jour = Integer.parseInt(scanner.nextLine());
        System.out.print("Heure début (0-23) : ");
        int heure = Integer.parseInt(scanner.nextLine());
        System.out.print("Minute début (0-59) : ");
        int minute = Integer.parseInt(scanner.nextLine());
        System.out.print("Durée (en minutes) : ");
        int duree = Integer.parseInt(scanner.nextLine());
        System.out.println("Lieu :");
        String lieu = scanner.nextLine();

        String participants = utilisateur;
        System.out.println("Ajouter un participant ? (oui / non)");
        while (scanner.nextLine().equals("oui")) {
            System.out.print("Participants : " + participants);
            participants += ", " + scanner.nextLine();
        }

        calendar.ajouterEvent(new Reunion(
                new TitreEvenement(titre), new Proprietaire(utilisateur),
                new DateEvenement(LocalDate.of(annee, mois, jour)),
                new HeureDebut(LocalTime.of(heure, minute)),
                new DureeEvenement(duree),
                new LieuEvenement(lieu),
                new Participants(participants.split(",\\s*"))
        ));
        System.out.println("Événement ajouté.");
    }

    private static void ajouterPeriodique() {
        System.out.print("Titre de l'événement : ");
        String titre = scanner.nextLine();
        System.out.print("Année (AAAA) : ");
        int annee = Integer.parseInt(scanner.nextLine());
        System.out.print("Mois (1-12) : ");
        int mois = Integer.parseInt(scanner.nextLine());
        System.out.print("Jour (1-31) : ");
        int jour = Integer.parseInt(scanner.nextLine());
        System.out.print("Heure début (0-23) : ");
        int heure = Integer.parseInt(scanner.nextLine());
        System.out.print("Minute début (0-59) : ");
        int minute = Integer.parseInt(scanner.nextLine());
        System.out.print("Frequence (en jours) : ");
        int frequence = Integer.parseInt(scanner.nextLine());

        calendar.ajouterEvent(new Periodique(
                new TitreEvenement(titre), new Proprietaire(utilisateur),
                new DateEvenement(LocalDate.of(annee, mois, jour)),
                new HeureDebut(LocalTime.of(heure, minute)),
                new DureeEvenement(0),
                new FrequenceRepetition(frequence)
        ));
        System.out.println("Événement ajouté.");
    }

    private static boolean verifierMotDePasse(String attendu) {
        System.out.print("Mot de passe: ");
        return scanner.nextLine().equals(attendu);
    }

    private static void afficherListe(List<Event> evenements) {
        boolean vide = evenements.isEmpty();
        String message = vide
                ? "Aucun événement trouvé pour cette période."
                : "Événements trouvés : ";
        System.out.println(message);
        evenements.forEach(e -> System.out.println("- " + e.description()));
    }
}
