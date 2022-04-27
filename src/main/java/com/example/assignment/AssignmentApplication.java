package com.example.assignment;

import com.google.common.cache.CacheBuilder;
import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachingConfigurer;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.concurrent.ConcurrentMapCache;
import org.springframework.cache.concurrent.ConcurrentMapCacheManager;
import org.springframework.cache.ehcache.EhCacheCacheManager;
import org.springframework.cache.support.SimpleCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.Collection;
import java.util.List;
import java.util.concurrent.TimeUnit;


@SpringBootApplication
@EnableCaching
@EnableScheduling
public class AssignmentApplication implements CachingConfigurer {
//	static int a =0;

	public static void main(String[] args) {
		SpringApplication.run(AssignmentApplication.class, args);


	}




	@Bean(name = "cacheManager")
	@Primary
	public org.springframework.cache.CacheManager cacheManager() {

		CacheManager cacheManager = new SimpleCacheManager() {

			@Override
			public Cache getCache(String name) {
				System.out.println(name);
				try {
//					System.out.println("in try");
//					a++;
//					if(a >= 3 )
//					{
//						throw new NoSuchElementException();
//					}
//					System.out.println(a);

					if (getEhcache().getCache(name) == null) {
						return getGuavaCache().getCache(name);
					}
					return getEhcache().getCache(name);
				}
				catch (Exception e )
				{
//					System.out.println("in catch" + e.getMessage());
//					System.out.println(getGuavaCache().getCache(name));
					return getGuavaCache().getCache(name);
				}

			}

			@Override
			public Collection<String> getCacheNames() {
				return List.of("firstLevel","com.example.assignment.entities.Employee","com.example.assignment.entities.Address","com.example.assignment.entities.Employee.addresses");
			}
		};

	return cacheManager;

	}



	@Bean
	public org.springframework.cache.CacheManager getEhcache() {
		CacheManager cacheManager = (org.springframework.cache.CacheManager) new EhCacheCacheManager();
		return cacheManager;

	}

	@Bean
	public org.springframework.cache.CacheManager getGuavaCache() {
		ConcurrentMapCacheManager cacheManager = new ConcurrentMapCacheManager() {

			@Override
			protected Cache createConcurrentMapCache(final String name) {
				return new ConcurrentMapCache(name,
						CacheBuilder.newBuilder().expireAfterWrite(30, TimeUnit.MINUTES).maximumSize(100).build().asMap(), false);
			}
		};

		cacheManager.setCacheNames(List.of("firstLevel","com.example.assignment.entities.Employee","com.example.assignment.entities.Address","com.example.assignment.entities.Employee.addresses"));

		return cacheManager;
	}


	@Bean
	public Mapper mapper()
	{
		Mapper map =   new DozerBeanMapper();

		return  map;
	}



	@CacheEvict(allEntries = true)
	public void clearAll(String name)
	{
		cacheManager().getCache(name).clear();
	}


	@Scheduled(cron = "0 01 00 *  * ?")
	public  void refreshCache()
	{
		cacheManager().getCacheNames().stream().forEach(cacheName ->{
			System.out.println(cacheName);
			clearAll(cacheName);

		});
	}

}
