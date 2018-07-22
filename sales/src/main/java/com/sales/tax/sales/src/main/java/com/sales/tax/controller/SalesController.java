package com.sales.tax.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sales.tax.service.SalesService;


/**
 * @author caio.rodrigo.santos
 *
 */

@RestController
public class SalesController {

	@Autowired
	SalesService salesService;
	final String DUPLICATED_ITENS = "This item is already registred.<br/>Use the following url to add or delete this item:";
	final String SUCCESS = "Item added with success"; 
	
	@RequestMapping("/sales/addProduct/{item}/{type}/{isImported}")
	public String addProduct(@PathVariable("item") String item, @PathVariable("type") Integer type, @PathVariable("isImported") Boolean isImported) {
		
		try {
		salesService.addProduct(item, type, isImported);
		} catch(DuplicateKeyException mwe) {
			return DUPLICATED_ITENS + 
					"<br/>Edit: /sales/editProduct/" + item + "/" + type + "/" + "/" + isImported + 
					"<br/>Delete: /sales/deleteProduct/" + item + "/" + type + "/" + isImported ;
			 
		}
		return SUCCESS;
	}
	
}
