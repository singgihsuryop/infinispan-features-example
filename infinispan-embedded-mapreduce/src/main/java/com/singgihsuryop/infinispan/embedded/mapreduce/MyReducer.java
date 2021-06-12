package com.singgihsuryop.infinispan.embedded.mapreduce;

import java.util.Iterator;

import org.infinispan.distexec.mapreduce.Reducer;

/**
 * Reduce all mapped entries by summing the item quantity
 */
public class MyReducer implements Reducer<String, Integer>{

	private static final long serialVersionUID = 1L;

	@Override
	public Integer reduce(String reducedKey, Iterator<Integer> iter) {
		System.out.println("Reducing : " +reducedKey );
		int totalQuantity = 0;
		
		while(iter.hasNext()){
			totalQuantity = totalQuantity + iter.next();
		}
		
		try {
			Thread.sleep(360000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return totalQuantity;
	}

}
