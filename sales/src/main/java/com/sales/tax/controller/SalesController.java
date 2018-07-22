package com.sales.tax.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sales.tax.domain.DTO.ProductDTO;
import com.sales.tax.domain.enumerations.MessageTypeEnum;
import com.sales.tax.domain.model.Message;
import com.sales.tax.domain.model.Product;
import com.sales.tax.service.SalesService;
import com.sales.tax.service.exception.ProductDoesntExistsException;


/**
 * 
 * Class used to control all operations about Sales. We are using this class to get user inputs and send it to the Service layer.
 * @author caio.rodrigo.santos
 *
 */

@RestController
public class SalesController {

	@Autowired
	SalesService salesService;
	final String DUPLICATED_ITENS = "This item is already registred.<br/>Use the following url to add or delete this item:";
	final String SUCCESS_ADD = "Item added with success"; 
	final String DEFAULT_ERROR = "An error occurred in your request.";
	final String SUCCESS_DELETE_ALL = "We deleted all products with success<br/>Total: ";
	final String SUCCESS_DELETE = "Item deleted successfully.";
	
	/**
	 * Method used to add a product to the database.
	 * @param item
	 * @param type
	 * @param isImported
	 * @param price
	 * @return
	 */
	@RequestMapping("/sales/addProduct/{item}/{type}/{isImported}/{price}")
	public ProductDTO addProduct(@PathVariable("item") String item, @PathVariable("type") Integer type, 
			@PathVariable("isImported") Boolean isImported, @PathVariable("price") Double price) {
		
		try {
			
			Product product = new Product();
			product.setCompositeKey(product.new CompositeKey(item, isImported, price));
			product.setType(type);
					
			salesService.addProduct(product);
			
		} catch(DuplicateKeyException mwe) {
			return new ProductDTO(null, null, null, new Message(MessageTypeEnum.ERROR.getType(), DUPLICATED_ITENS + 
							"<br/>Edit: /sales/editProduct/" + item + "/" + type + "/" + "/" + isImported + "/" + price +
							"<br/>Delete: /sales/deleteProduct/" + item + "/" + type + "/" + isImported + "/" + price));
					
			 
		}
		catch(Exception ex) {
			return new ProductDTO(null, null, null, new Message(MessageTypeEnum.ERROR.getType(), DEFAULT_ERROR));
		}
		return new ProductDTO(null, null, null, new Message(MessageTypeEnum.SUCCESS.getType(), SUCCESS_ADD));
	}
	

	/**
	 * Method used to delete a specific product in the database.
	 * @param item
	 * @param isImported
	 * @param price
	 * @return
	 */
	@RequestMapping("/sales/deleteProduct/{item}/{isImported}/{price}")
	public ProductDTO deleteProduct(@PathVariable("item") String item, @PathVariable("isImported") Boolean isImported, @PathVariable("price") Double price) {
		
		try {
		
		Product product = new Product();
		product.setCompositeKey(product.new CompositeKey(item, isImported, price));
			
		salesService.deleteProduct(product);
		}
		catch(ProductDoesntExistsException pde) {
			return new ProductDTO(null, null, null, new Message(MessageTypeEnum.ERROR.getType(), pde.getMessage()));
			 
		}
		catch(Exception ex) {
			return new ProductDTO(null, null, null, new Message(MessageTypeEnum.ERROR.getType(), DEFAULT_ERROR));
		}
		return new ProductDTO(null, null, null, new Message(MessageTypeEnum.SUCCESS.getType(), SUCCESS_DELETE));
	}
	
	
	/**
	 * Method used to delete all products in the database.
	 * @return
	 */
	@RequestMapping("/sales/deleteAll/")
	public ProductDTO deleteAll() {
		Long registers;
		try {
		registers = salesService.deleteAll();
		} catch(Exception ex) {
			return new ProductDTO(null, null, null, new Message(MessageTypeEnum.ERROR.getType(), DEFAULT_ERROR));
			 
		}
		return new ProductDTO(null, null, null, new Message(MessageTypeEnum.SUCCESS.getType(), SUCCESS_DELETE_ALL + registers)); 
	}
	
	/**
	 * Method used to list all products in the database.
	 * @return
	 */
	@RequestMapping("/sales/listAll/")
	public String listAll() {
		try {
		return salesService.listAll().toString();
		} catch(Exception ex) {
			return DEFAULT_ERROR;
			 
		}
	}
	
	/**
	 * Main method to this challenge. We use it to purchase a product just like the document.
	 * @param item
	 * @param quantity
	 * @param isImported
	 * @param price
	 * @return
	 */
	@RequestMapping("/sales/purchase/{item}/{quantity}/{isImported}/{price}")
	public ProductDTO purchase(@PathVariable("item") String item, @PathVariable("quantity") Integer quantity, 
			@PathVariable("isImported") Boolean isImported, @PathVariable("price") Double price) {
		
		try {
			
			Product product = new Product();
			product.setCompositeKey(product.new CompositeKey(item, isImported, price));
			return salesService.purchase(product, quantity);
			
		} catch(ProductDoesntExistsException pde) {
			 return new ProductDTO(null, null, null, new Message(MessageTypeEnum.ERROR.getType(), pde.getMessage()));
		}
		catch(Exception ex) {
			return new ProductDTO(null, null, null, new Message(MessageTypeEnum.ERROR.getType(), DEFAULT_ERROR));
		}
	}
	
}
