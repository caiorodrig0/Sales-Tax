package com.sales.tax.domain.model;

/**
 * 
 * @author caio.rodrigo.santos
 *
 */
public class Message {

	private Integer Id;
	private String description;
	
	public Message(Integer id, String description) {
		super();
		Id = id;
		this.description = description;
	}
	
	public Integer getId() {
		return Id;
	}
	public void setId(Integer id) {
		Id = id;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	@Override
	public String toString() {
		return "Message [Id=" + Id + ", description=" + description + "]";
	}
	
	
	
}
