package com.example.assignment;

import net.sf.ehcache.config.CacheConfiguration;
import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachingConfigurer;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.ehcache.EhCacheCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;


@SpringBootApplication
@EnableCaching
@EnableScheduling
public class AssignmentApplication implements CachingConfigurer {

	public static void main(String[] args) {
		SpringApplication.run(AssignmentApplication.class, args);


	}




//	@Bean
//	public net.sf.ehcache.CacheManager ehCacheManager() {
//		CacheConfiguration cacheConfiguration = new CacheConfiguration();
//		cacheConfiguration.setName("firstLevel");
//
//		cacheConfiguration.setMaxEntriesLocalHeap(1000);
//		cacheConfiguration.setTimeToLiveSeconds(15);
//		cacheConfiguration.timeToIdleSeconds(15);
//
//
////
////		CacheConfiguration cacheConfiguration2 = new CacheConfiguration();
////		cacheConfiguration2.setName("employee");
////		cacheConfiguration2.diskPersistent(true);
////		cacheConfiguration2.diskSpoolBufferSizeMB(1000);
////		cacheConfiguration2.setMaxEntriesLocalHeap(1000);
////		cacheConfiguration2.eternal(true);
//////		cacheConfiguration2.setTimeToLiveSeconds(1000);
//////		cacheConfiguration2.internalSetTimeToLive(1000);
////
////
////		CacheConfiguration cacheConfiguration3 = new CacheConfiguration();
////		cacheConfiguration3.setName("address");
////		cacheConfiguration3.diskPersistent(true);
////		cacheConfiguration3.diskSpoolBufferSizeMB(1000);
////		cacheConfiguration3.setMaxEntriesLocalHeap(1000);
////		cacheConfiguration3.eternal(true);
//////		cacheConfiguration3.setTimeToLiveSeconds(1000);
//////		cacheConfiguration3.internalSetTimeToLive(1000);
//
//
//		net.sf.ehcache.config.Configuration config = new net.sf.ehcache.config.Configuration();
//		config.addCache(cacheConfiguration);
////		config.addCache(cacheConfiguration2);
////		config.addCache(cacheConfiguration3);
////		config.addCache(cacheConfiguration3);
//
//		return net.sf.ehcache.CacheManager.create(config);
//	}

	@Bean
	public org.springframework.cache.CacheManager cacheManager() {
		return (org.springframework.cache.CacheManager) new EhCacheCacheManager();
	}
//
//	@Bean(name = "cacheManagerB")
//	public org.springframework.cache.CacheManager cacheManagerB() {
//		return (org.springframework.cache.CacheManager) new EhCacheCacheManager(ehCacheManager());
//	}


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
