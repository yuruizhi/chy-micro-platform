package com.changyi.properties;

import org.springframework.boot.autoconfigure.cache.CacheProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.List;
import java.util.Map;


@ConfigurationProperties(prefix = "custom.cache-manager")
public class CustomCacheProperties {
    private Map<String, CacheProperties.Redis> customCaches;


    public Map<String, CacheProperties.Redis> getCustomCaches() {
        return customCaches;
    }

    public void setCustomCaches(Map<String, CacheProperties.Redis> customCaches) {
        this.customCaches = customCaches;
    }
}
