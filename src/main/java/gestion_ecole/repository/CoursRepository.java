package gestion_ecole.repository;

import gestion_ecole.core.repository.Repository;
import gestion_ecole.entite.Cours;
import java.util.List;

public interface CoursRepository extends Repository<Cours> {
    List<Cours> findByNiveauId(int niveauId); 
    List<Cours> findByClasseId(int classeId);
    List<Cours> findByProfesseurId(int profId);
    boolean assignCoursToClasse(int coursId, int classeId);

}
