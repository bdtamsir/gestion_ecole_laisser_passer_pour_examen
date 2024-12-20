package gestion_ecole.repository;

import gestion_ecole.core.repository.Repository;
import gestion_ecole.entite.Classe;
import java.util.List;

public interface ClasseRepository extends Repository<Classe> {
    List<Classe> selectByNiveauId(int niveauId);
    Classe findByName(String nom);
}
