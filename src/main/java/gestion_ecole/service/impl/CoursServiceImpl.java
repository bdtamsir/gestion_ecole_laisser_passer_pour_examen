package gestion_ecole.service.impl;

import gestion_ecole.entite.Cours;
import gestion_ecole.repository.CoursRepository;
import gestion_ecole.service.CoursService;

import java.util.List;

public class CoursServiceImpl implements CoursService {
    private final CoursRepository coursRepository;

    public CoursServiceImpl(CoursRepository coursRepository) {
        this.coursRepository = coursRepository;
    }

    @Override
    public boolean create(Cours c) {
        return coursRepository.insert(c);
    }

    @Override
    public List<Cours> getAll() {
        return coursRepository.selectAll();
    }

    @Override
    public List<Cours> getByNiveauId(int niveauId) {
        return coursRepository.findByNiveauId(niveauId);
    }

    @Override
    public List<Cours> getByClasseId(int classeId) {
        return coursRepository.findByClasseId(classeId);
    }

    @Override
    public List<Cours> getByProfesseurId(int profId) {
        return coursRepository.findByProfesseurId(profId);
    }

    @Override
    public boolean assignCoursToClasse(int coursId, int classeId) {
        return coursRepository.assignCoursToClasse(coursId, classeId);
    }
}
