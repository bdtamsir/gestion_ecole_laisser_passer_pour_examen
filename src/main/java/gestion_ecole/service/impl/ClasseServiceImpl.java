package gestion_ecole.service.impl;

import gestion_ecole.entite.Classe;
import gestion_ecole.repository.ClasseRepository;
import gestion_ecole.service.ClasseService;

import java.util.List;

public class ClasseServiceImpl implements ClasseService {
    private final ClasseRepository classeRepository;

    public ClasseServiceImpl(ClasseRepository classeRepository){
        this.classeRepository=classeRepository;
    }

    @Override
    public boolean create(Classe c) {
        return classeRepository.insert(c);
    }

    @Override
    public List<Classe> getAll() {
        return classeRepository.selectAll();
    }

    @Override
    public List<Classe> getByNiveauId(int niveauId) {
        return classeRepository.selectByNiveauId(niveauId);
    }

    @Override
    public Classe findByName(String nom) {
        return classeRepository.findByName(nom);
    }
}
