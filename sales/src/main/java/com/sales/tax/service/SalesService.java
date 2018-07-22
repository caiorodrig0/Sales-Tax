package com.sales.tax.service;

import java.util.List;

import com.mongodb.MongoWriteException;
import com.sales.tax.domain.DTO.ProductDTO;
import com.sales.tax.domain.model.Product;
import com.sales.tax.service.exception.ProductDoesntExistsException;

/**
 * 
 * Interface to keep our Sales services
 * @author caio.rodrigo.santos
 *
 */
public interface SalesService {

	/**
	 * Method used to add a product to the database.
	 * @param product
	 * @throws MongoWriteException
	 */
	void addProduct(Product product) throws MongoWriteException;

	/**
	 * Method used to delete a specific product in the database.
	 * @return
	 */
	Long deleteAll();

	/**
	 * Method used to delete all products in the database.
	 * @param product
	 * @throws ProductDoesntExistsException
	 */
	void deleteProduct(Product product) throws ProductDoesntExistsException;

	/**
	 * Method used to list all products in the database.
	 * @return
	 */
	List<Product> listAll();

	/**
	 * Main method to this challenge. We use it to purchase a product just like the document.
	 * @param product
	 * @param quantity
	 * @return
	 * @throws ProductDoesntExistsException
	 */
	ProductDTO purchase(Product product, Integer quantity) throws ProductDoesntExistsException;
}
