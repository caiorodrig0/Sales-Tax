package com.sales.tax.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.stereotype.Service;

import com.sales.tax.repository.ProductRepository;

@EnableMongoRepositories({"com.sales.tax.repository"})
@Service
public class SalesServiceImpl implements SalesService {
	
	@Autowired
	private ProductRepository productRepository;



	@Override
	public String addProduct(String item, Integer type) {
		return "Item: " + item.toString() + "\nType: " + type;
	}

}
