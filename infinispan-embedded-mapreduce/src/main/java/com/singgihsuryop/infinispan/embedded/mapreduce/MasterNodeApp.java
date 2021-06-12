package com.singgihsuryop.infinispan.embedded.mapreduce;

import java.util.List;
import java.util.UUID;
import java.util.concurrent.Future;
import java.util.logging.Logger;

import org.infinispan.Cache;
import org.infinispan.distexec.mapreduce.MapReduceTask;
import org.infinispan.manager.DefaultCacheManager;
import org.infinispan.manager.EmbeddedCacheManager;

public class MasterNodeApp {

	
	public static void main(String args[]) throws Exception {
		System.out.println("Running MasterNode Application");
		System.setProperty("java.net.preferIPv4Stack", "true");

		EmbeddedCacheManager manager = new DefaultCacheManager("infinispan-7.2-config.xml");
		Cache<String, Item> cache = manager.getCache("itemCache");

		Item item1 = new Item(generateId(), "Pencil", 2);
		cache.put(item1.getId(), item1);
		
		Item item2 = new Item(generateId(), "Book", 3);
		cache.put(item2.getId(), item2);
		
		Item item3 = new Item(generateId(), "Pencil", 4);
		cache.put(item3.getId(), item3);
		
		Item item4 = new Item(generateId(), "Pencil", 6);
		cache.put(item4.getId(), item4);
		
		Item item5 = new Item(generateId(), "Pen", 2);
		cache.put(item5.getId(), item5);
		
		Item item6 = new Item(generateId(), "Eraser", 2);
		cache.put(item6.getId(), item6);
		
		Item item7 = new Item(generateId(), "Ruler", 2);
		cache.put(item7.getId(), item7);
		
		Item item8 = new Item(generateId(), "Ruler", 2);
		cache.put(item8.getId(), item8);
		
		Item item9 = new Item(generateId(), "Paper", 2);
		cache.put(item9.getId(), item9);

		MapReduceTask<String, Item, String, Integer> mapreducetask = 
				//new MapReduceTask<>(cache) //multi node mapper, single node reducer
				new MapReduceTask<String, Item, String, Integer>(cache, true) //multi node mapper, multi node reducer
		;
		
		
//		List<ItemInventory> inventory = mapreducetask
//				.mappedWith(new MyMapper())
//				.reducedWith(new MyReducer())
//				.execute(new MyCollator())
//				;
//		System.out.println(inventory.toString());
		
		Future<List<ItemInventory>> inventory = mapreducetask
				.mappedWith(new MyMapper())
				.reducedWith(new MyReducer())
				.executeAsynchronously(new MyCollator())
				;
		System.out.println(inventory.get().toString());
		
		cache.clear();
	}
	
	private static String generateId(){
		return UUID.randomUUID().toString();
	}
	
}
