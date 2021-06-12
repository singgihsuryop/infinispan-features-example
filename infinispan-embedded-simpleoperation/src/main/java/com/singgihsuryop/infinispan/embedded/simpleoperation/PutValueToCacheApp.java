package com.singgihsuryop.infinispan.embedded.simpleoperation;

import java.util.UUID;

import org.infinispan.Cache;
import org.infinispan.manager.DefaultCacheManager;
import org.infinispan.manager.EmbeddedCacheManager;

public class PutValueToCacheApp {

	public static void main(String args[]) throws Exception {
		System.out.println("Running PutValueToCache Application");
		System.setProperty("java.net.preferIPv4Stack", "true");

		//This will create a new instance
		EmbeddedCacheManager manager = new DefaultCacheManager("infinispan-7.2-config.xml");
		Cache<String, Person> cache = manager.getCache("personCache");

		Person person1 = new Person();
		person1.setId(UUID.randomUUID().toString());
		person1.setName("John");
		person1.setAge(25);
		cache.put(person1.getId(), person1);
		System.out.println("New person successfully added");
		
		System.exit(0); //Closing application (Destroying an infinispan instance)
	}
}
