package gestion_ecole.service.impl;

import gestion_ecole.entite.Professeur;
import gestion_ecole.repository.ProfesseurRepository;
import gestion_ecole.service.ProfesseurService;

import java.util.List;

public class ProfesseurServiceImpl implements ProfesseurService {
    private final ProfesseurRepository professeurRepository;

    public ProfesseurServiceImpl(ProfesseurRepository professeurRepository){
        this.professeurRepository=professeurRepository;
    }

    @Override
    public boolean create(Professeur p) {
        return professeurRepository.insert(p);
    }

    @Override
    public List<Professeur> getAll() {
        return professeurRepository.selectAll();
    }

    @Override
    public Professeur findByEmail(String email) {
        return professeurRepository.findByEmail(email);
    }
}
