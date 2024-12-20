package gestion_ecole.repository;

import gestion_ecole.core.repository.Repository;
import gestion_ecole.entite.Professeur;
import java.util.List;

public interface ProfesseurRepository extends Repository<Professeur> {
    Professeur findByEmail(String email);
    @Override
    List<Professeur> selectAll();
}
