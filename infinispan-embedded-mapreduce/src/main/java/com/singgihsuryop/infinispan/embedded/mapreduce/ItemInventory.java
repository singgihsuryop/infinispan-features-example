package com.singgihsuryop.infinispan.embedded.mapreduce;

import java.io.Serializable;

/**
 * @author <a href="mailto:singgih.prasetyo@sigma.co.id">Singgih Suryo Prasetyo</a> since Feb 25, 2017 4:18:09 PM
 * @version $Id: $
 * 
 */
public class ItemInventory implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	String name;
	int totalQuantity;
	
	public ItemInventory(String name, int totalQuantity) {
		this.name = name;
		this.totalQuantity = totalQuantity;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getTotalQuantity() {
		return totalQuantity;
	}
	public void setTotalQuantity(int totalQuantity) {
		this.totalQuantity = totalQuantity;
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("{\"name\"=\"");
		builder.append(name);
		builder.append("\", \"totalQuantity\"=\"");
		builder.append(totalQuantity);
		builder.append("\"}");
		return builder.toString();
	}
	
}
