package com.sales.tax.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.stereotype.Service;

import com.mongodb.MongoWriteException;
import com.sales.tax.domain.DTO.ProductDTO;
import com.sales.tax.domain.enumerations.MessageTypeEnum;
import com.sales.tax.domain.enumerations.ProductTypeEnum;
import com.sales.tax.domain.model.Message;
import com.sales.tax.domain.model.Product;
import com.sales.tax.repository.ProductRepository;
import com.sales.tax.service.exception.ProductDoesntExistsException;
import com.sales.tax.service.util.ServiceUtil;
import com.sales.tax.service.validator.ValidatorUtil;

/**
 * @author caio.rodrigo.santos 
 * This class is utilized to manage the rules for
 *         sales. We are using this to make all our business logic.
 */
@EnableMongoRepositories({ "com.sales.tax.repository" })
@Service
public class SalesServiceImpl implements SalesService {

	@Autowired
	private ProductRepository productRepository;

	final String PRODUCT_DOESNT_EXIST = "The product informed does not exist in our database.";

	/* (non-Javadoc)
	 * @see com.sales.tax.service.SalesService#addProduct(com.sales.tax.domain.model.Product)
	 */
	@Override
	public void addProduct(Product product) throws MongoWriteException {
		productRepository.insert(product);
	}

	/* (non-Javadoc)
	 * @see com.sales.tax.service.SalesService#deleteProduct(com.sales.tax.domain.model.Product)
	 */
	@Override
	public void deleteProduct(Product product)
			throws ProductDoesntExistsException {
		if (ValidatorUtil.isNull(productRepository.findByCompositeKey(product
				.getCompositeKey())))
			throw new ProductDoesntExistsException(PRODUCT_DOESNT_EXIST);

		productRepository.deleteByCompositeKey(product.getCompositeKey());
	}

	/* (non-Javadoc)
	 * @see com.sales.tax.service.SalesService#deleteAll()
	 */
	@Override
	public Long deleteAll() {

		Long registers = productRepository.count();
		productRepository.deleteAll();
		return registers;
	}

	/* (non-Javadoc)
	 * @see com.sales.tax.service.SalesService#listAll()
	 */
	@Override
	public List<Product> listAll() {
		return productRepository.findAll();
	}

	/* (non-Javadoc)
	 * @see com.sales.tax.service.SalesService#purchase(com.sales.tax.domain.model.Product, java.lang.Integer)
	 */
	@Override
	public ProductDTO purchase(Product product, Integer quantity)
			throws ProductDoesntExistsException {

		product = productRepository.findByCompositeKey(product
				.getCompositeKey());
		double taxRate = 0;
		double taxValue = 0;

		if (ValidatorUtil.isNull(product))
			throw new ProductDoesntExistsException(PRODUCT_DOESNT_EXIST);

		if (product.getCompositeKey().getIsImported()) {
			taxRate = 0.05;
		}

		if (!ProductTypeEnum.isExempted(product.getType())) {
			taxRate = taxRate + 0.1;
		}

		taxValue = ServiceUtil.getRoundedTaxValue(product.getCompositeKey()
				.getPrice(), taxRate);
		product.getCompositeKey().setPrice(
				ServiceUtil
						.round(product.getCompositeKey().getPrice(), taxRate));

		return new ProductDTO(product, taxValue, quantity, new Message(
				MessageTypeEnum.SUCCESS.getType(), "Success"));

	}

}
