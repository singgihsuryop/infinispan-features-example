package com.singgihsuryop.infinispan.embedded.mapreduce;

import org.infinispan.distexec.mapreduce.Collector;
import org.infinispan.distexec.mapreduce.Mapper;

/**
 * Mapping all item
 */
public class MyMapper implements Mapper<String, Item, String, Integer>{

	private static final long serialVersionUID = 1L;

	@Override
	public void map(String key, Item value, Collector<String, Integer> collector) {
		System.out.println("Mapping : " +value);
		collector.emit(value.getName(), value.getQuantity());
	}

}
