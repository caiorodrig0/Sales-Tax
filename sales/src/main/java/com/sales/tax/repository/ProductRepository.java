package com.sales.tax.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.sales.tax.domain.model.Product;
import com.sales.tax.domain.model.Product.CompositeKey;

/**
 * 
 * We are using this class to keep all custom operations for Product entity.
 * @author caio.rodrigo.santos
 *
 */
@Repository
public interface ProductRepository extends MongoRepository<Product, String> {
	
	Product findByCompositeKey(CompositeKey compKey);
	void deleteByCompositeKey(CompositeKey compkey); 
}
