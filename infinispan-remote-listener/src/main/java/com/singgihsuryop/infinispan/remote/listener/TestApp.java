package com.singgihsuryop.infinispan.remote.listener;

import java.util.UUID;

import org.infinispan.client.hotrod.RemoteCache;
import org.infinispan.client.hotrod.RemoteCacheManager;
import org.infinispan.client.hotrod.configuration.Configuration;
import org.infinispan.client.hotrod.configuration.ConfigurationBuilder;

public class TestApp {

	public static void main(String args[]) throws Exception {
		System.out.println("Running Test Application");
		System.setProperty("java.net.preferIPv4Stack", "true");

		//Configuration configuration = new ConfigurationBuilder().addServer().host("127.0.0.1").port(11222).build();
		Configuration configuration = new ConfigurationBuilder().addServers("127.0.0.1:11322").addServers("127.0.0.1:11422").build();
		
		RemoteCacheManager cacheManager = new RemoteCacheManager(configuration);
		RemoteCache<String, Person> cache = cacheManager.getCache("PERSON_CACHE");
		
		cache.addClientListener(new PersonCacheListener());

		String personId = UUID.randomUUID().toString();
		
		Person person1 = new Person();
		person1.setId(personId);
		person1.setName("Singgih");
		person1.setAge(25);
		cache.put(person1.getId(), person1); //adding new Person object
		
		person1.setAge(26);
		cache.put(person1.getId(), person1); //add modified Person object

		cache.remove(person1.getId()); //remove person
	}
}
