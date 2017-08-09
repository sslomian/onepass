package pl.sscode.onepass.repository.api.service;

import java.util.List;

/**
 * Created by sscode on 2017-06-15.
 */
public interface RepositoryService<V, K> {
    V save(V object);

    V getById(K id);

    List<V> findAll();

    void delete(K id);
}
