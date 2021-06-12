package com.singgihsuryop.infinispan.remote.simpleoperation;

import java.util.UUID;

import org.infinispan.client.hotrod.RemoteCache;
import org.infinispan.client.hotrod.RemoteCacheManager;
import org.infinispan.client.hotrod.configuration.Configuration;
import org.infinispan.client.hotrod.configuration.ConfigurationBuilder;

public class PutValueToCache {

	public static void main(String args[]) throws Exception {
		System.out.println("Running PutValueToCache App");
		System.setProperty("java.net.preferIPv4Stack", "true");

		//Configuration configuration = new ConfigurationBuilder().addServer().host("127.0.0.1").port(11222).build();
		Configuration configuration = new ConfigurationBuilder().addServers("127.0.0.1:11322").addServers("127.0.0.1:11422").build();
		
		RemoteCacheManager cacheManager = new RemoteCacheManager(configuration);
		RemoteCache<String, Person> cache = cacheManager.getCache("PERSON_CACHE");

		Person person1 = new Person();
		person1.setId(UUID.randomUUID().toString());
		person1.setName("Singgih");
		person1.setAge(25);
		cache.put(person1.getId(), person1);
		System.out.println("Success put person 1 to cache");
		
	}
}
