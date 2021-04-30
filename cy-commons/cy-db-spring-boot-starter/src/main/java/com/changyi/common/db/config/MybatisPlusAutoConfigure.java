package com.changyi.common.db.config;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import com.changyi.common.db.properties.MybatisPlusAutoFillProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;

/**
 * mybatis-plus自动配置
 *
 * @author YuRuizhi
 * @date 2020.4.5
 */
@EnableConfigurationProperties(MybatisPlusAutoFillProperties.class)
public class MybatisPlusAutoConfigure {

    @Autowired
    private MybatisPlusAutoFillProperties autoFillProperties;

    @Bean
    @ConditionalOnMissingBean
    @ConditionalOnProperty(prefix = "cy.mybatis-plus.auto-fill", name = "enabled", havingValue = "true", matchIfMissing = true)
    public MetaObjectHandler metaObjectHandler() {
        return new DateMetaObjectHandler(autoFillProperties);
    }
}
