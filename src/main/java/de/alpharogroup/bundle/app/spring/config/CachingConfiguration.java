package de.alpharogroup.bundle.app.spring.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CachingConfigurer;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.interceptor.CacheErrorHandler;
import org.springframework.cache.interceptor.CacheResolver;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import de.alpharogroup.springconfig.SpringCacheConfigurationFactory;

@Configuration
@EnableCaching
public class CachingConfiguration implements CachingConfigurer
{

	@Qualifier("ehCacheCacheManager")
	@Autowired(required = false)
	private CacheManager ehCacheCacheManager;

	@Qualifier("redisCacheManager")
	@Autowired(required = false)
	private CacheManager redisCacheManager;

	@Bean
	@Override
	public CacheManager cacheManager()
	{
		return SpringCacheConfigurationFactory.newCacheManager(this.ehCacheCacheManager,
			this.redisCacheManager);
	}


	@Override
	public CacheResolver cacheResolver()
	{
		return SpringCacheConfigurationFactory.newSimpleCacheResolver();
	}


	@Override
	public CacheErrorHandler errorHandler()
	{
		return SpringCacheConfigurationFactory.newSimpleCacheErrorHandler();
	}


	@Bean
	@Override
	public KeyGenerator keyGenerator()
	{
		return SpringCacheConfigurationFactory.newSimpleKeyGenerator();
	}
}