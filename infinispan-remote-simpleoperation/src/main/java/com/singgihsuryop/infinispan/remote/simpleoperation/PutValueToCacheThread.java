package com.singgihsuryop.infinispan.remote.simpleoperation;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.infinispan.client.hotrod.RemoteCache;
import org.infinispan.client.hotrod.RemoteCacheManager;
import org.infinispan.client.hotrod.configuration.Configuration;
import org.infinispan.client.hotrod.configuration.ConfigurationBuilder;

public class PutValueToCacheThread {

	public static void main(String args[]) throws Exception {
		System.out.println("Running PutValueToCache App");
		System.setProperty("java.net.preferIPv4Stack", "true");

		Configuration configuration = new ConfigurationBuilder().addServer().host("127.0.0.1").port(11222).build();
//		Configuration configuration = new ConfigurationBuilder().addServers("127.0.0.1:11322").addServers("127.0.0.1:11422").build();
		
		RemoteCacheManager cacheManager = new RemoteCacheManager(configuration);
		RemoteCache<String, Person> cache = cacheManager.getCache("namedCache");
		cache.clear();
		
		ExecutorService executors = Executors.newFixedThreadPool(10);
		List<Person> p = new ArrayList<>();
		for(int i = 0;i<2010;i++){
			Person person1 = new Person();
			person1.setId(UUID.randomUUID().toString());
			person1.setName("Singgih");
			person1.setAge(25);
			p.add(person1);
		}
		
		for(Person psr : p){
//			System.out.println(psr.toString());
			executors.submit(new PutValueToCacheThread().new Ayam(cache, psr));
		}
		System.out.println("sukses");
	}
	
	private class Ayam implements Runnable{

		RemoteCache<String, Person> cache;
		Person person;
		
		public Ayam(RemoteCache<String, Person> cache, Person person){
			this.cache=cache;
			this.person=person;
		}
		@Override
		public void run() {
			cache.put(person.getId(), person);
			
		}
		
	}
	
}
