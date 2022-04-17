package com.example.assignment;


import net.sf.ehcache.config.CacheConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.CachingConfigurer;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.ehcache.EhCacheCacheManager;
import org.springframework.context.annotation.Bean;

import javax.cache.CacheManager;


@SpringBootApplication
@EnableCaching
public class AssignmentApplication implements CachingConfigurer {

	public static void main(String[] args) {
		SpringApplication.run(AssignmentApplication.class, args);


	}

	@Bean
	public net.sf.ehcache.CacheManager ehCacheManager() {
		CacheConfiguration cacheConfiguration = new CacheConfiguration();
		cacheConfiguration.setName("searchByEmployeeId");
		cacheConfiguration.setMaxEntriesLocalHeap(1000);
		cacheConfiguration.setTimeToLiveSeconds(30);
		net.sf.ehcache.config.Configuration config = new net.sf.ehcache.config.Configuration();
		config.addCache(cacheConfiguration);


		return net.sf.ehcache.CacheManager.newInstance(config);
	}

	@Bean
	@Override
	public org.springframework.cache.CacheManager cacheManager() {
		return (org.springframework.cache.CacheManager) new EhCacheCacheManager(ehCacheManager());
	}
	

}
