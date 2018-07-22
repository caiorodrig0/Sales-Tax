package com.sales.tax.domain.DTO;

import com.sales.tax.domain.model.Message;
import com.sales.tax.domain.model.Product;

/**
 * DTO utilized to product operations. 
 * @author caio.rodrigo.santos
 *
 */
public class ProductDTO {

	private Product product;
	private Double taxValue;
	private Integer quantity;
	private Message message;

	public ProductDTO(Product product, Double taxValue, Integer quantity,
			Message message) {
		super();
		this.product = product;
		this.taxValue = taxValue;
		this.quantity = quantity;
		this.message = message;
	}

	public ProductDTO(Product product, Message message) {
		super();
		this.product = product;
		this.message = message;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public Double getTaxValue() {
		return taxValue;
	}

	public void setTaxValue(Double taxValue) {
		this.taxValue = taxValue;
	}

	public Message getMessage() {
		return message;
	}

	public void setMessage(Message message) {
		this.message = message;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	@Override
	public String toString() {
		return quantity + " " + product.getCompositeKey().getItem() + " " + product.getCompositeKey().getPrice();
	}

}
