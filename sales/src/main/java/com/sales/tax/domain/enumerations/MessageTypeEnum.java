package com.sales.tax.domain.enumerations;

/**
 * 
 * Enum used to keep message types.
 * @author caio.rodrigo.santos
 *
 */
public enum MessageTypeEnum {

	SUCCESS(1),
	ERROR(2),
	ALERT(3);
	
	
	private Integer type;
	
	MessageTypeEnum(Integer type) {
		this.type = type;
	}
	
	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}
	
	
}
 