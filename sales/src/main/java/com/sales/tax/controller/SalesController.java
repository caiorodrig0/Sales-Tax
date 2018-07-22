package com.sales.tax.controller;

import org.springframework.beans.factory.annotation.Autowired;
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
	
	
	@RequestMapping("/sales/addProduct/{item}/{type}")
	public String addProduct(@PathVariable("item") String item, @PathVariable("type") Integer type) {
		return salesService.addProduct(item, type);
	}
	
}
