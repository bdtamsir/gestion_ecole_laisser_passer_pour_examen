package gestion_ecole.service;

import gestion_ecole.core.service.Service;
import gestion_ecole.entite.Classe;
import java.util.List;

public interface ClasseService extends Service<Classe> {
    List<Classe> getByNiveauId(int niveauId);
    Classe findByName(String nom);
}
