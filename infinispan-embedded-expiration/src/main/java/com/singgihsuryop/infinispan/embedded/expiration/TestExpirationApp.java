package com.singgihsuryop.infinispan.embedded.expiration;

import java.util.Map;
import java.util.UUID;

import org.infinispan.Cache;
import org.infinispan.manager.DefaultCacheManager;
import org.infinispan.manager.EmbeddedCacheManager;

public class TestExpirationApp {

	public static void main(String args[]) throws Exception {
		System.out.println("Running Test Expiration Application");
		System.setProperty("java.net.preferIPv4Stack", "true");

		EmbeddedCacheManager cacheManager = new DefaultCacheManager("infinispan-7.2-config.xml");
		
		Cache<String, Integer> testCache = cacheManager.getCache("testCache");
		putValueToCache(testCache);
		showCacheEntries(testCache);
		
		System.out.println("Sleep for 5 sec...");
		Thread.sleep(5000);
		showCacheEntries(testCache);
		
		System.out.println("Sleep again for 5 sec...");
		Thread.sleep(5000);
		showCacheEntries(testCache);
		
		System.exit(0);
	}
	
	private static void putValueToCache(Cache<String, Integer> cache){
		for(int i = 1 ; i <= 15 ; i++){
			cache.put(generateId(), i);
		}
	}
	
	private static void showCacheEntries(Cache<String, Integer> cache){
		String entries = "";
		for(Map.Entry<String, Integer> entry : cache.entrySet()){
			entries = entries.concat(entry.getValue() + " ");
		}
		System.out.println("Print the result on cache " +cache.getName());
		System.out.println(entries);
		System.out.println("");
	}
	
	private static String generateId(){
		return UUID.randomUUID().toString();
	}
	
}
