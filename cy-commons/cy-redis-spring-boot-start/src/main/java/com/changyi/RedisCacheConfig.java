package com.changyi;

import com.changyi.properties.CustomCacheProperties;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.cache.CacheProperties;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.data.redis.RedisAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

@EnableCaching
@Configuration
@ConditionalOnClass({CacheProperties.Redis.class, RedisCacheConfiguration.class})
@EnableConfigurationProperties({CacheProperties.class, CustomCacheProperties.class})
public class RedisCacheConfig {

    @Autowired
    private CustomCacheProperties customCacheProperties;

    @Autowired
    private CacheProperties cacheProperties;

    @Bean
    @SuppressWarnings("all")
    public RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory factory)
            throws UnknownHostException {
        // 我们为了自己开发方便，一般直接使用 <String, Object>
        RedisTemplate<String, Object> template = new RedisTemplate<String,
                Object>();
        template.setConnectionFactory(factory);
        // Json序列化配置
        Jackson2JsonRedisSerializer jackson2JsonRedisSerializer = new
                Jackson2JsonRedisSerializer(Object.class);
        ObjectMapper om = new ObjectMapper();
        om.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
        om.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);
        jackson2JsonRedisSerializer.setObjectMapper(om);
        // String 的序列化
        StringRedisSerializer stringRedisSerializer = new
                StringRedisSerializer();
        // key采用String的序列化方式
        template.setKeySerializer(stringRedisSerializer);
        // hash的key也采用String的序列化方式
        template.setHashKeySerializer(stringRedisSerializer);
        // value序列化方式采用jackson
        template.setValueSerializer(jackson2JsonRedisSerializer);
        // hash的value序列化方式采用jackson
        template.setHashValueSerializer(jackson2JsonRedisSerializer);
        template.afterPropertiesSet();
        return template;
    }

    @Bean
    @Primary
    public CacheManager cacheManager(RedisConnectionFactory redisConnectionFactory
            ) {

        RedisCacheConfiguration defaultConfiguration = getDefaultRedisCacheConfiguration();

        //自定义的缓存过期时间配置
        Map<String, RedisCacheConfiguration> map = new HashMap();
        Optional.ofNullable(customCacheProperties)
                .map(p -> p.getCustomCaches())
                .ifPresent(customCaches -> {
                    customCaches.forEach((key, cache) -> {
                        RedisCacheConfiguration cfg = handleRedisCacheConfiguration(cache, defaultConfiguration);
                        map.put(key, cfg);
                    });
                });


        return RedisCacheManager.builder(redisConnectionFactory)
                .withInitialCacheConfigurations(map)
                .cacheDefaults(defaultConfiguration)
                .build();
    }

    @Bean
    public KeyGenerator keyGenerator() {
        return (target, method, objects) -> {
            StringBuilder sb = new StringBuilder();
            sb.append(target.getClass().getName());
            sb.append(":" + method.getName() + ":");
            for (Object obj : objects) {
                sb.append(obj.toString());
            }
            return sb.toString();
        };
    }

    /**
     * 缓存key建构建,使用参数以“:” 分割
     *
     * @param
     * @return
     */
    @Bean("DefaultKeyGenerator")
    public KeyGenerator keyGenerator1() {
        return (target, method, objects) -> {
            StringBuilder sb = new StringBuilder();
            for (Object obj : objects) {
                sb.append(":"+obj.toString());
            }
            if(sb.toString().startsWith(":")){
                return sb.toString().substring(1);
            }
            return sb.toString();
        };
    }

    private Jackson2JsonRedisSerializer getJackson2JsonRedisSerializer() {
        Jackson2JsonRedisSerializer jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer(Object.class);
        ObjectMapper om = new ObjectMapper();
        om.setVisibility(PropertyAccessor.GETTER, JsonAutoDetect.Visibility.ANY);
        om.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);
        om.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        om.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        jackson2JsonRedisSerializer.setObjectMapper(om);
        return jackson2JsonRedisSerializer;
    }

    private RedisCacheConfiguration getDefaultRedisCacheConfiguration() {

        CacheProperties.Redis redisProperties = cacheProperties.getRedis();
        RedisCacheConfiguration config = RedisCacheConfiguration.defaultCacheConfig();

        Jackson2JsonRedisSerializer<Object> jackson2JsonRedisSerializer = getJackson2JsonRedisSerializer();
        config = config.serializeKeysWith(RedisSerializationContext.SerializationPair.fromSerializer(new StringRedisSerializer()));
        config = config.serializeValuesWith(RedisSerializationContext.SerializationPair.fromSerializer(jackson2JsonRedisSerializer));
        config = handleRedisCacheConfiguration(redisProperties, config);
        return config;
    }


    private RedisCacheConfiguration handleRedisCacheConfiguration(CacheProperties.Redis redisProperties,
                                                                  RedisCacheConfiguration config) {
        if (Objects.isNull(redisProperties)) {
            return config;
        }

        if (redisProperties.getTimeToLive() != null) {
            config = config.entryTtl(redisProperties.getTimeToLive());
        }
        if (redisProperties.getKeyPrefix() != null) {
            config = config.computePrefixWith(cacheName -> redisProperties.getKeyPrefix());
        }
        if (!redisProperties.isCacheNullValues()) {
            config = config.disableCachingNullValues();
        }
        if (!redisProperties.isUseKeyPrefix()) {
            config = config.disableKeyPrefix();
        }
        return config;
    }
}
