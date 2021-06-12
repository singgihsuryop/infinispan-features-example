package com.singgihsuryop.infinispan.embedded.mapreduce;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.infinispan.distexec.mapreduce.Collator;

/**
 * Collate reduced entries from the Reducer Class. This is the final result
 */
public class MyCollator implements Collator<String, Integer, List<ItemInventory>>{

	@Override
	public List<ItemInventory> collate(Map<String, Integer> reducedResults) {
		System.out.println("Collate");
		List<ItemInventory> listInventory = new ArrayList<>();
		
		for(Map.Entry<String, Integer> entry : reducedResults.entrySet()){
			listInventory.add(new ItemInventory(entry.getKey(), entry.getValue()));
		}
		
		return listInventory;
	}
	
}
