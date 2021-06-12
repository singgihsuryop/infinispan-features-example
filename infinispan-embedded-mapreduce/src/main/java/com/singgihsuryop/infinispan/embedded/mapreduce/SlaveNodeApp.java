package com.singgihsuryop.infinispan.embedded.mapreduce;

import org.infinispan.manager.DefaultCacheManager;
import org.infinispan.manager.EmbeddedCacheManager;

public class SlaveNodeApp {
	
	public static void main(String args[]) throws Exception {
		System.out.println("Running SlaveNode Application");
		System.setProperty("java.net.preferIPv4Stack", "true");

		EmbeddedCacheManager manager = new DefaultCacheManager("infinispan-7.2-config.xml");
		manager.getCache("itemCache");
	}//sudo route add -net 228.6.7.8/32 -interface lo0
}
