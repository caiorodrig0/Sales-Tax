package com.sales.tax.service.exception;

/**
 * 
 * Custom exception to prevent a search or delete a product that does not exists in database.
 * @author caio.rodrigo.santos
 *
 */
public class ProductDoesntExistsException extends Exception {

	
	private static final long serialVersionUID = 2598159427475322286L;

	public ProductDoesntExistsException(String msg) {
		super(msg);
	}
}
