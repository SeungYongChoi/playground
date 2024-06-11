package com.yong.playground.cache;

import com.yong.playground.cache.domain.Parent;
import com.yong.playground.cache.dto.CacheTestResponse;
import com.yong.playground.cache.repository.ChildRepository;
import com.yong.playground.cache.repository.ParentRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
@RequiredArgsConstructor
@Slf4j
public class CacheTestService {

    private final ParentRepository parentRepository;
    private final ChildRepository childRepository;
    private final RedisTemplate<String, Object> redisTemplate;

    public CacheTestResponse cacheTest() {
        log.info("service method start");
        String key = "Redis_Key";
        CacheTestResponse cacheData = (CacheTestResponse) redisTemplate.opsForValue().get(key);

        if (cacheData != null) {
            log.info("cache hit!");
            return cacheData;
        }

        log.info("cache miss!");
        Parent parent = parentRepository.findTopByOrderByCreateTimeDesc().orElseThrow(EntityNotFoundException::new);

        CacheTestResponse ret = CacheTestResponse.from(parent);
        redisTemplate.opsForValue().set(key, ret, 3, TimeUnit.SECONDS);

        return ret;
    }
}
