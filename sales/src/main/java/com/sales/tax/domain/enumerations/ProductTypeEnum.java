package com.sales.tax.domain.enumerations;

/**
 * 
 * Enum used to keep product types
 * @author caio.rodrigo.santos
 *
 */
public enum ProductTypeEnum {

	BOOK(1),
	FOOD(2),
	MEDICAL(3),
	OTHER(4);
	
	private Integer type;
	
	private ProductTypeEnum(Integer type) {
		this.type = type;
	}
	
	
	public static Boolean isExempted(Integer type) {
		
		for (ProductTypeEnum tpe : values()) {
			if(type.equals(tpe.getType()) && !type.equals(tpe.OTHER.getType())) return true;
		}
		
		return false;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}
	
	
}
 