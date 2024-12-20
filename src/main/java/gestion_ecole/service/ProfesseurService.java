package gestion_ecole.service;

import gestion_ecole.core.service.Service;
import gestion_ecole.entite.Professeur;

public interface ProfesseurService extends Service<Professeur> {
    Professeur findByEmail(String email);
}
