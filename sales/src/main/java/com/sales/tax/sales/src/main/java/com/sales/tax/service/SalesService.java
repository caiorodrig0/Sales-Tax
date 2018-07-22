package com.sales.tax.service;

import com.mongodb.MongoWriteException;

public interface SalesService {

	void addProduct(String item, Integer type, Boolean isImported) throws MongoWriteException;
}
