package pl.sscode.onepass.repository.api.repository.impl.service;

import org.slf4j.Logger;
import org.springframework.data.jpa.repository.JpaRepository;
import pl.sscode.onepass.repository.api.service.RepositoryService;

import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;

import static org.slf4j.LoggerFactory.getLogger;

/**
 * Created by sscode on 2017-06-15.
 */
public abstract class AbstractRepositoryService<V, T, K extends Serializable, S extends JpaRepository<T, K>>
        implements RepositoryService<V, K> {

    private final Logger logger = getLogger(AbstractRepositoryService.class);

    protected S repository;

    public AbstractRepositoryService(S repository) {
        this.repository = repository;
    }

    @Override
    public V save(V dto) {
        logger.info("Saving object: {}", dto);
        T entity = convertToEntity(dto);
        T saved = repository.save(entity);
        return convertToDto(saved);
    }

    @Override
    public V getById(K id) {
        logger.debug("Searching for acount with id: {} ", id);
        T entity = repository.findOne(id);
        logger.info("Founded account: {}", entity);
        return convertToDto(entity);
    }

    @Override
    public List<V> findAll() {
        List<T> entities = repository.findAll();
        return entities.stream().map(this::convertToDto).collect(Collectors.toList());
    }

    protected abstract T convertToEntity(V dto);

    protected abstract V convertToDto(T entity);

}
