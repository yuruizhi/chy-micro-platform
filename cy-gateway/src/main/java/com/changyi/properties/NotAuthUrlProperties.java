package com.changyi.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.LinkedHashSet;

/**
 * 类的描述:网关跳过认证的配置类
 *
 */
@Data
@ConfigurationProperties("cy.gateway")
public class NotAuthUrlProperties {

    private LinkedHashSet<String> shouldSkipUrls;
}
