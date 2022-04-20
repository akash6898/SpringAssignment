package com.example.assignment;

import net.sf.ehcache.config.CacheConfiguration;
import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.CachingConfigurer;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.ehcache.EhCacheCacheManager;
import org.springframework.cache.support.NoOpCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;


@SpringBootApplication
@EnableCaching
public class AssignmentApplication implements CachingConfigurer {

	public static void main(String[] args) {
		SpringApplication.run(AssignmentApplication.class, args);


	}




	@Bean
	public net.sf.ehcache.CacheManager ehCacheManager() {
		CacheConfiguration cacheConfiguration = new CacheConfiguration();
		cacheConfiguration.setName("firstLevel");

		cacheConfiguration.setMaxEntriesLocalHeap(1000);
		cacheConfiguration.setTimeToLiveSeconds(15);



		CacheConfiguration cacheConfiguration2 = new CacheConfiguration();
		cacheConfiguration2.setName("employee");
		cacheConfiguration2.diskPersistent(true);
		cacheConfiguration2.diskSpoolBufferSizeMB(1000);
		cacheConfiguration2.setMaxEntriesLocalHeap(1000);
		cacheConfiguration2.eternal(true);
		cacheConfiguration2.setTimeToLiveSeconds(1000);
//		CacheConfiguration cacheConfiguration3 = new CacheConfiguration();
//		cacheConfiguration3.setName("address");
//		cacheConfiguration3.setMaxEntriesLocalHeap(1000);
//		cacheConfiguration3.setTimeToLiveSeconds(75);

		net.sf.ehcache.config.Configuration config = new net.sf.ehcache.config.Configuration();
		config.addCache(cacheConfiguration);
		config.addCache(cacheConfiguration2);

//		config.addCache(cacheConfiguration3);

		return net.sf.ehcache.CacheManager.create(config);
	}

	@Bean
	@Profile("!test")
	public org.springframework.cache.CacheManager cacheManager() {
		return (org.springframework.cache.CacheManager) new EhCacheCacheManager(ehCacheManager());
	}

	@Bean
	@Profile("test")
	public org.springframework.cache.CacheManager getcacheManager() {
		return (org.springframework.cache.CacheManager) new NoOpCacheManager();
	}

	@Bean
	public Mapper mapper()
	{
		Mapper map =   new DozerBeanMapper();

		return  map;
	}








//	@Bean
//	public  org.hibernate.cache.ehcache.internal.EhcacheRegionFactory  regionFactory()
//	{
//		return new EhcacheRegionFactory();
//	}


}
