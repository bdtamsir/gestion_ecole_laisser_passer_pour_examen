package gestion_ecole.service.impl;

import gestion_ecole.entite.Niveau;
import gestion_ecole.repository.NiveauRepository;
import gestion_ecole.service.NiveauService;

import java.util.List;

public class NiveauServiceImpl implements NiveauService {

    private final NiveauRepository niveauRepository;

    public NiveauServiceImpl(NiveauRepository niveauRepository) {
        this.niveauRepository = niveauRepository;
    }

    @Override
    public boolean create(Niveau n) {
        return niveauRepository.insert(n);
    }

    @Override
    public List<Niveau> getAll() {
        return niveauRepository.selectAll();
    }

    @Override
    public Niveau findByName(String nom) {
        return niveauRepository.findByName(nom);
    }
}
