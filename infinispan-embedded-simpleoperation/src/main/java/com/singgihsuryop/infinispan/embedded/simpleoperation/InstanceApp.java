package com.singgihsuryop.infinispan.embedded.simpleoperation;

import java.util.Map;

import org.infinispan.Cache;
import org.infinispan.manager.DefaultCacheManager;
import org.infinispan.manager.EmbeddedCacheManager;

public class InstanceApp {

	public static void main(String args[]) throws Exception {
		System.out.println("Running Instance Application");
		System.setProperty("java.net.preferIPv4Stack", "true");

		EmbeddedCacheManager manager = new DefaultCacheManager("infinispan-7.2-config.xml");
		Cache<String, Person> cache = manager.getCache("personCache");
		while(true){
			System.out.println("Count entries : " +cache.size());
			
			System.out.println("Show entries");
			for(Map.Entry<String, Person> entry : cache.entrySet()){
				System.out.println(entry.getValue());
			}
			
			System.out.println("============");
			Thread.sleep(5000); 
		}

	}
}
