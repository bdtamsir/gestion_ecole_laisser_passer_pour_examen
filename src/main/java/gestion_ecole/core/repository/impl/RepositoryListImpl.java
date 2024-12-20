package gestion_ecole.core.repository.impl;

import gestion_ecole.core.repository.Repository;
import java.util.ArrayList;
import java.util.List;

public class RepositoryListImpl<T> implements Repository<T> {
    protected List<T> data = new ArrayList<>();

    @Override
    public boolean insert(T value) {
        return data.add(value);
    }

    @Override
    public List<T> selectAll() {
        return data;
    }
}
