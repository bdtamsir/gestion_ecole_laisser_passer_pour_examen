package gestion_ecole.view;

import gestion_ecole.entite.*;
import gestion_ecole.service.*;

import java.util.List;

public class AdminView extends View {

    private final NiveauService niveauService;
    private final ClasseService classeService;
    private final ProfesseurService professeurService;
    private final CoursService coursService;

    public AdminView(NiveauService niveauService, ClasseService classeService, ProfesseurService professeurService, CoursService coursService) {
        this.niveauService = niveauService;
        this.classeService = classeService;
        this.professeurService = professeurService;
        this.coursService = coursService;
    }

    public void createNiveau() {
        String nomNiveau = askString("Entrez le nom du niveau:");
        Niveau n = new Niveau();
        n.setNom(nomNiveau);
        if (niveauService.create(n)) {
            System.out.println("Niveau créé avec succès ! ID=" + n.getId());
        } else {
            System.out.println("Erreur lors de la création du niveau.");
        }
    }

    public void createClasse() {
        // Afficher les niveaux
        List<Niveau> niveaux = niveauService.getAll();
        if (niveaux.isEmpty()) {
            System.out.println("Aucun niveau n'existe. Veuillez d'abord créer un niveau.");
            return;
        }

        System.out.println("Liste des niveaux :");
        for (int i = 0; i < niveaux.size(); i++) {
            System.out.println((i + 1) + " - " + niveaux.get(i).getNom() + " (ID=" + niveaux.get(i).getId() + ")");
        }
        int choix = askInt("Choisissez le niveau (index)");
        if (choix < 1 || choix > niveaux.size()) {
            System.out.println("Choix invalide.");
            return;
        }
        Niveau chosenNiveau = niveaux.get(choix - 1);

        String nomClasse = askString("Entrez le nom de la classe:");
        Classe c = new Classe();
        c.setNom(nomClasse);
        c.setNiveau_id(chosenNiveau.getId());
        if (classeService.create(c)) {
            System.out.println("Classe créée avec succès ! ID=" + c.getId());
        } else {
            System.out.println("Erreur lors de la création de la classe.");
        }
    }

    public void createProfesseur() {
        String nom = askString("Entrez le nom du professeur:");
        String prenom = askString("Entrez le prénom du professeur:");
        String email = askString("Entrez l'email du professeur:");
        Professeur p = new Professeur();
        p.setNom(nom);
        p.setPrenom(prenom);
        p.setEmail(email);
        if (professeurService.create(p)) {
            System.out.println("Professeur créé avec succès ! ID=" + p.getId());
        } else {
            System.out.println("Erreur lors de la création du professeur.");
        }
    }

    public void createCours() {
        List<Professeur> profs = professeurService.getAll();
        if (profs.isEmpty()) {
            System.out.println("Aucun professeur. Veuillez d'abord créer un professeur.");
            return;
        }

        System.out.println("Liste des professeurs:");
        for (int i = 0; i < profs.size(); i++) {
            System.out.println((i + 1) + " - " + profs.get(i).getNom() + " " + profs.get(i).getPrenom() + " (ID=" + profs.get(i).getId() + ")");
        }
        int choixP = askInt("Choisissez un professeur (index):");
        if (choixP < 1 || choixP > profs.size()) {
            System.out.println("Choix invalide.");
            return;
        }
        Professeur chosenProf = profs.get(choixP - 1);

        String nomCours = askString("Entrez le nom du cours:");
        String module = askString("Entrez le nom du module:");
        Cours c = new Cours();
        c.setNom(nomCours);
        c.setModule(module);
        c.setProfesseur_id(chosenProf.getId());

        if (coursService.create(c)) {
            System.out.println("Cours créé avec succès ! ID=" + c.getId());

            // Ici je le mets même si cest optionnel
            assignCoursToClasses(c.getId());
        } else {
            System.out.println("Erreur lors de la création du cours.");
        }
    }

    private void assignCoursToClasses(int coursId) {
        // J'affich les classes
        List<Classe> classes = classeService.getAll();
        if (classes.isEmpty()) {
            System.out.println("Aucune classe. Vous pouvez en créer avant d'associer le cours.");
            return;
        }
        System.out.println("Liste des classes:");
        for (int i = 0; i < classes.size(); i++) {
            System.out.println((i + 1) + " - " + classes.get(i).getNom() + " (ID=" + classes.get(i).getId() + ")");
        }
        int choixC = askInt("Choisissez une classe (index) ou 0 pour finir:");
        while (choixC != 0) {
            if (choixC < 1 || choixC > classes.size()) {
                System.out.println("Choix invalide.");
            } else {
                Classe chosenClasse = classes.get(choixC - 1);
                boolean ok = coursService.assignCoursToClasse(coursId, chosenClasse.getId());
                if (ok) System.out.println("Cours associé à la classe " + chosenClasse.getNom());
            }
            choixC = askInt("Choisissez une autre classe (index) ou 0 pour finir:");
        }
    }

    public void afficherCoursParNiveau() {
        List<Niveau> niveaux = niveauService.getAll();
        if (niveaux.isEmpty()) {
            System.out.println("Aucun niveau disponible.");
            return;
        }
        System.out.println("Liste des niveaux:");
        for (int i = 0; i < niveaux.size(); i++) {
            System.out.println((i + 1) + " - " + niveaux.get(i).getNom());
        }
        int choix = askInt("Choisissez un niveau (index):");
        if (choix < 1 || choix > niveaux.size()) {
            System.out.println("Choix invalide.");
            return;
        }
        Niveau niv = niveaux.get(choix - 1);
        List<Cours> coursList = coursService.getByNiveauId(niv.getId());
        System.out.println("Cours du niveau " + niv.getNom() + ":");
        coursList.forEach(System.out::println);
    }

    public void afficherCoursParClasse() {
        List<Classe> classes = classeService.getAll();
        if (classes.isEmpty()) {
            System.out.println("Aucune classe disponible.");
            return;
        }
        System.out.println("Liste des classes:");
        for (int i = 0; i < classes.size(); i++) {
            System.out.println((i + 1) + " - " + classes.get(i).getNom());
        }
        int choix = askInt("Choisissez une classe (index):");
        if (choix < 1 || choix > classes.size()) {
            System.out.println("Choix invalide.");
            return;
        }
        Classe c = classes.get(choix - 1);
        List<Cours> coursList = coursService.getByClasseId(c.getId());
        System.out.println("Cours de la classe " + c.getNom() + ":");
        coursList.forEach(System.out::println);
    }

    public void afficherCoursParProfesseur() {
        List<Professeur> profs = professeurService.getAll();
        if (profs.isEmpty()) {
            System.out.println("Aucun professeur.");
            return;
        }
        System.out.println("Liste des professeurs:");
        for (int i = 0; i < profs.size(); i++) {
            System.out.println((i + 1) + " - " + profs.get(i).getNom() + " " + profs.get(i).getPrenom());
        }
        int choix = askInt("Choisissez un professeur (index):");
        if (choix < 1 || choix > profs.size()) {
            System.out.println("Choix invalide.");
            return;
        }
        Professeur p = profs.get(choix - 1);
        List<Cours> coursList = coursService.getByProfesseurId(p.getId());
        System.out.println("Cours du professeur " + p.getNom() + " " + p.getPrenom() + ":");
        coursList.forEach(System.out::println);
    }
}
