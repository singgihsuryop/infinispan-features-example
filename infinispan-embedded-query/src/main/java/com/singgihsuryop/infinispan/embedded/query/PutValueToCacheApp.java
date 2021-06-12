package com.singgihsuryop.infinispan.embedded.query;

import java.util.UUID;

import org.infinispan.Cache;
import org.infinispan.manager.DefaultCacheManager;
import org.infinispan.manager.EmbeddedCacheManager;

public class PutValueToCacheApp {

	public static void main(String args[]) throws Exception {
		System.out.println("Running InfinispanEmbeddedPut App");
		System.setProperty("java.net.preferIPv4Stack", "true");

		EmbeddedCacheManager manager = new DefaultCacheManager("infinispan-7.2-config.xml");
		Cache<String, Person> cache = manager.getCache("personCache");

		Person person1 = new Person(generateId(), "Singgih", 25);
		cache.put(person1.getId(), person1);
		System.out.println("Success put person "+person1 +" to cache");
		
		Person person2 = new Person(generateId(), "Shawn", 22);
		cache.put(person2.getId(), person2);
		System.out.println("Success put person "+person2 +" to cache");
		
		Person person3 = new Person(generateId(), "Sierra", 55);
		cache.put(person3.getId(), person3);
		System.out.println("Success put person "+person3 +" to cache");
		
		Person person4 = new Person(generateId(), "Simone", 25);
		cache.put(person4.getId(), person4);
		System.out.println("Success put person "+person4 +" to cache");
		
		Person person5 = new Person(generateId(), "Susi", 25);
		cache.put(person5.getId(), person5);
		System.out.println("Success put person "+person5 +" to cache");
		
	}
	
	private static String generateId(){
		return UUID.randomUUID().toString();
	}
}
