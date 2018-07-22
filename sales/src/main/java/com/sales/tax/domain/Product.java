package com.sales.tax.domain;

import org.springframework.data.annotation.Id;

public class Product {

	@Id
	private String id;
	private String item;
	private Integer type;

	public Product() {}

	public Product(String item, Integer type) {
		this.item = item;
		this.type = type;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getItem() {
		return item;
	}

	public void setItem(String item) {
		this.item = item;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	@Override
	public String toString() {
		return "Product [id=" + id + ", item=" + item + ", type=" + type + "]";
	}
	
	
}
