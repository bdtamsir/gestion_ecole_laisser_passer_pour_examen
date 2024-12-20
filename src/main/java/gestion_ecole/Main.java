package gestion_ecole;

import gestion_ecole.repository.*;
import gestion_ecole.repository.bd.*;
import gestion_ecole.service.*;
import gestion_ecole.service.impl.*;
import gestion_ecole.view.*;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        View.setScanner(scanner);

        // Instanciation des repositories
        NiveauRepository niveauRepository = new NiveauRepositoryBd();
        ClasseRepository classeRepository = new ClasseRepositoryBd();
        ProfesseurRepository professeurRepository = new ProfesseurRepositoryBd();
        CoursRepository coursRepository = new CoursRepositoryBd();

        // J'instancie les services
        NiveauService niveauService = new NiveauServiceImpl(niveauRepository);
        ClasseService classeService = new ClasseServiceImpl(classeRepository);
        ProfesseurService professeurService = new ProfesseurServiceImpl(professeurRepository);
        CoursService coursService = new CoursServiceImpl(coursRepository);

        // Je mets la vue admin
        AdminView adminView = new AdminView(niveauService, classeService, professeurService, coursService);

        int choice;
        do {
            System.out.println("MENU ADMIN");
            System.out.println("1. Créer un niveau");
            System.out.println("2. Créer une classe");
            System.out.println("3. Créer un professeur");
            System.out.println("4. Créer un cours");
            System.out.println("5. Afficher les cours d'un niveau");
            System.out.println("6. Afficher les cours d'une classe");
            System.out.println("7. Afficher les cours d'un professeur");
            System.out.println("8. Quitter");

            choice = adminView.askInt("Votre choix:");

            switch(choice) {
                case 1 -> adminView.createNiveau();
                case 2 -> adminView.createClasse();
                case 3 -> adminView.createProfesseur();
                case 4 -> adminView.createCours();
                case 5 -> adminView.afficherCoursParNiveau();
                case 6 -> adminView.afficherCoursParClasse();
                case 7 -> adminView.afficherCoursParProfesseur();
                case 8 -> System.out.println("Au revoir.");
                default -> System.out.println("Choix invalide.");
            }

        } while (choice != 8);
    }
}
