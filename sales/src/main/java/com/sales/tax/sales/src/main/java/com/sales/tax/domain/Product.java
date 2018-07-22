package com.sales.tax.domain;

import java.io.Serializable;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.annotation.Id;

public class Product {

	
	@Id
	private CompositeKey compositeKey;
	private Integer type;
	

	public Product() {}

	


	public Product(CompositeKey compositeKey, Integer type) {
		super();
		this.compositeKey = compositeKey;
		this.type = type;
	}




	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}




	public CompositeKey getCompositeKey() {
		return compositeKey;
	}




	public void setCompositeKey(CompositeKey compositeKey) {
		this.compositeKey = compositeKey;
	}




	 public class CompositeKey implements Serializable {
		private String item;
		private Boolean isImported;
		
		public CompositeKey(String item, Boolean isImported) {
			super();
			this.item = item;
			this.isImported = isImported;
		}

		public String getItem() {
			return item;
		}

		public void setItem(String item) {
			this.item = item;
		}

		public Boolean getIsImported() {
			return isImported;
		}

		public void setIsImported(Boolean isImported) {
			this.isImported = isImported;
		}
		
		
		
	}
	
}
