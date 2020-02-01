package practice.cache.config;

import com.github.benmanes.caffeine.cache.Caffeine;
import com.github.benmanes.caffeine.cache.RemovalCause;
import com.github.benmanes.caffeine.cache.RemovalListener;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.CacheManager;
import org.springframework.cache.caffeine.CaffeineCache;
import org.springframework.cache.support.SimpleCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PreDestroy;
import java.util.Arrays;
import java.util.concurrent.TimeUnit;

@Slf4j
@Configuration
public class CaffeineConfig {

    private static final int EXPIRE_1S = 1;
    private static final int EXPIRE_1M = 60;
    private static final int EXPIRE_1H = 3600;

    @Bean
    public CacheManager cacheManager() {
        SimpleCacheManager manager = new SimpleCacheManager();
        manager.setCaches(
            Arrays.asList(
                buildCache("getExhibitionWithTheme", EXPIRE_1M)
            ));
        return manager;
    }

    private CaffeineCache buildCache(String name, int secondsToExpire) {
        return new CaffeineCache(name, Caffeine.newBuilder()
                                               .expireAfterWrite(secondsToExpire, TimeUnit.SECONDS)
                                               .build());
    }

    @PreDestroy
    public void clearCache() {
        cacheManager().getCacheNames().stream()
                      .forEach(cache -> cacheManager().getCache(cache).clear());
    }

    class CustomRemovalListener implements RemovalListener<Object, Object> {
        @Override
        public void onRemoval(Object key, Object value, RemovalCause cause) {
            log.info("removal called with key {}, cause {}, evicted {}\n", key, cause.toString(), cause.wasEvicted());
        }
    }
}