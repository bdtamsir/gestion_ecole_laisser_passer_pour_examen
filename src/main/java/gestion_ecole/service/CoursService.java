package gestion_ecole.service;

import gestion_ecole.core.service.Service;
import gestion_ecole.entite.Cours;

import java.util.List;

public interface CoursService extends Service<Cours> {
    List<Cours> getByNiveauId(int niveauId);
    List<Cours> getByClasseId(int classeId);
    List<Cours> getByProfesseurId(int profId);
    boolean assignCoursToClasse(int coursId, int classeId);
}
