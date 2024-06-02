package ru.shulenin.simple_messanger.repository.redis;

import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;
import ru.shulenin.simple_messanger.entity.User;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
@RequiredArgsConstructor
public class UserRedisRepository implements RedisRepository<String, String> {
    private static final String KEY = "USER_LOGIN";

    private final RedisTemplate<String, String> redisTemplate;


    @Override
    public boolean existsById(String id) {
        return redisTemplate.opsForHash().get(KEY, id) != null;
    }

    @Override
    public void save(String id) {
        redisTemplate.opsForHash().put(KEY, id, id);
    }

    @Override
    public void delete(String id) {
        redisTemplate.opsForHash().delete(KEY, id);
    }

    @Override
    public boolean isEmpty() {
        return redisTemplate.opsForHash().values(KEY).isEmpty();
    }

    @Override
    public void clear() {
        redisTemplate.delete(KEY);
    }
}
