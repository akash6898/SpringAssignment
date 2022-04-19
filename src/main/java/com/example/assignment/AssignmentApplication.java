package com.example.assignment;


import com.example.assignment.entities.Address;
import com.example.assignment.entities.Employee;
import net.sf.ehcache.config.CacheConfiguration;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cache.ehcache.internal.EhcacheRegionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.CachingConfigurer;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.ehcache.EhCacheCacheManager;
import org.springframework.cache.support.NoOpCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;

import javax.cache.CacheManager;



@SpringBootApplication
@EnableCaching
public class AssignmentApplication implements CachingConfigurer {

	public static void main(String[] args) {
		SpringApplication.run(AssignmentApplication.class, args);


	}


//	private static SessionFactory sessionFactory;
//
//	private  SessionFactory buildSessionFactory() {
//		try {
//			// Create the SessionFactory from hibernate.cfg.xml
//			Configuration configuration = new Configuration();
//			configuration.addAnnotatedClass(Employee.class);
//			configuration.addAnnotatedClass(Address.class);
//			configuration.configure();
//			System.out.println("Hibernate Configuration loaded");
//
//			ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties()).build();
//			System.out.println("Hibernate serviceRegistry created");
//
//			SessionFactory sessionFactory = configuration.buildSessionFactory(serviceRegistry);
//
//			return sessionFactory;
//		}
//		catch (Throwable ex) {
//			System.err.println("Initial SessionFactory creation failed." + ex);
//			ex.printStackTrace();
//			throw new ExceptionInInitializerError(ex);
//		}
//	}
//
//	@Autowired
//	public  SessionFactory getSessionFactory() {
//		if(sessionFactory == null) sessionFactory = buildSessionFactory();
//		return sessionFactory;
//	}

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




//	@Bean
//	public  org.hibernate.cache.ehcache.internal.EhcacheRegionFactory  regionFactory()
//	{
//		return new EhcacheRegionFactory();
//	}


}
