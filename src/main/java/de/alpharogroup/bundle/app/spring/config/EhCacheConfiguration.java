package de.alpharogroup.bundle.app.spring.config;

import org.springframework.cache.ehcache.EhCacheCacheManager;
import org.springframework.cache.ehcache.EhCacheManagerFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import de.alpharogroup.springconfig.SpringCacheConfigurationFactory;

@Configuration
public class EhCacheConfiguration {

    @Bean
    public EhCacheCacheManager ehCacheCacheManager() {
        return SpringCacheConfigurationFactory.newEhCacheCacheManager(ehCacheManagerFactoryBean());
    }


    @Bean
    public EhCacheManagerFactoryBean ehCacheManagerFactoryBean() {
        return SpringCacheConfigurationFactory.newEhCacheManagerFactoryBean("ehcache.xml");
    }
}