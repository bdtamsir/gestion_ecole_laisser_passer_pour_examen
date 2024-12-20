package gestion_ecole.repository;

import gestion_ecole.core.repository.Repository;
import gestion_ecole.entite.Niveau;

public interface NiveauRepository extends Repository<Niveau> {
    Niveau findByName(String nom);
}
