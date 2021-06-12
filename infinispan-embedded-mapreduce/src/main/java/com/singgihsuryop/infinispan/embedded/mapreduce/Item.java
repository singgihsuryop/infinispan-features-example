package com.singgihsuryop.infinispan.embedded.mapreduce;

import java.io.Serializable;

/**
 * @author <a href="mailto:singgih.prasetyo@sigma.co.id">Singgih Suryo Prasetyo</a> since Feb 21, 2017 10:40:41 AM
 * @version $Id: $
 * 
 */
public class Item implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private String id;
	private String name;
	private int quantity;
	
	public Item(String id, String name, int quantity) {
		this.id = id;
		this.name = name;
		this.quantity = quantity;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("{\"id\"=\"");
		builder.append(id);
		builder.append("\", \"name\"=\"");
		builder.append(name);
		builder.append("\", \"quantity\"=\"");
		builder.append(quantity);
		builder.append("\"}");
		return builder.toString();
	}

}
