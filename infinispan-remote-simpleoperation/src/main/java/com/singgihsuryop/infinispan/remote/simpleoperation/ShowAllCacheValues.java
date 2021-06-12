package com.singgihsuryop.infinispan.remote.simpleoperation;

import org.infinispan.client.hotrod.RemoteCache;
import org.infinispan.client.hotrod.RemoteCacheManager;
import org.infinispan.client.hotrod.configuration.Configuration;
import org.infinispan.client.hotrod.configuration.ConfigurationBuilder;

public class ShowAllCacheValues {

	public static void main(String args[]) throws Exception {
		System.out.println("Running ShowAllCacheValues App");
		System.setProperty("java.net.preferIPv4Stack", "true");

		//Configuration configuration = new ConfigurationBuilder().addServer().host("127.0.0.1").port(11222).build();
		Configuration configuration = new ConfigurationBuilder().addServers("127.0.0.1:11322").addServers("127.0.0.1:11422").build();
		
		RemoteCacheManager cacheManager = new RemoteCacheManager(configuration);
		RemoteCache<String, Person> cache = cacheManager.getCache("PERSON_CACHE");
		
		for(String key : cache.keySet()){
			System.out.println(cache.get(key));
		}
		
	}
}
