package com.sales.tax.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.stereotype.Service;
import com.mongodb.MongoWriteException;
import com.sales.tax.domain.Product;
import com.sales.tax.repository.ProductRepository;

@EnableMongoRepositories({"com.sales.tax.repository"})
@Service
public class SalesServiceImpl implements SalesService {
	
	@Autowired
	private ProductRepository productRepository;



	@Override
	public void addProduct(String item, Integer type, Boolean isImported) throws MongoWriteException {

		Product product = new Product();
		product.setCompositeKey(product.new CompositeKey(item, true));
		product.setType(1);
		productRepository.insert(product);
		
	}

}
