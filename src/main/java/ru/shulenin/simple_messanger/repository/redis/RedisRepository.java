package ru.shulenin.simple_messanger.repository.redis;

import java.util.List;
import java.util.Optional;

public interface RedisRepository<E, K> {
    public boolean existsById(K id);

    public void save(E entity);

    public void delete(K id);

    public boolean isEmpty();

    public void clear();

}
