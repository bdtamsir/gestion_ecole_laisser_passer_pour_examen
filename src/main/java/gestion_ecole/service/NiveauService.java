package gestion_ecole.service;

import gestion_ecole.core.service.Service;
import gestion_ecole.entite.Niveau;

public interface NiveauService extends Service<Niveau> {
    Niveau findByName(String nom);
}
