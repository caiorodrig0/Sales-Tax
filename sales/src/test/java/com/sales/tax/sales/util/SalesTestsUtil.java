package com.sales.tax.sales.util;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.sales.tax.domain.DTO.ProductDTO;
import com.sales.tax.domain.model.Product;
import com.sales.tax.service.util.ServiceUtil;

/**
 * Helper for Unit Tests layer
 * @author caio.rodrigo.santos
 *
 */
public final class SalesTestsUtil {

	private static final String REGEX = "(\\d+)\\s((\\w+\\s)+)at\\s(\\d+.\\d+)";
	
	/**
	 * Main method used to split the values into the object
	 * @param entry
	 * @return
	 */
	public static Product parser(String entry) {
		Product product = new Product();
		Matcher matcher = parse(entry);
		String item = matcher.group(2).trim();
		
		if(item.contains("imported"))
		product.setCompositeKey(product.new CompositeKey(item, true, Double.valueOf(matcher.group(4))));
		else 
	    product.setCompositeKey(product.new CompositeKey(item, false, Double.valueOf(matcher.group(4))));

		return product;
	}
	
	/**
	 * Method utilized to find the groups
	 * @param description
	 * @return
	 */
	public static Matcher parse(String description) {
		Pattern pattern = Pattern.compile(REGEX);
		Matcher matcher = pattern.matcher(description);
		matcher.find();
		return matcher;
	}
	
	/**
	 * Method used to print the results just like the document.
	 * @param products
	 */
	public static void printSalesTax(List<ProductDTO> products) {
		Double totalTaxValue = new Double(0);
		Double totalValue = new Double(0);
		
		for (ProductDTO productDTO : products) {
			System.out.println(productDTO);
			totalTaxValue = totalTaxValue + productDTO.getTaxValue();
			totalValue = totalValue + productDTO.getProduct().getCompositeKey().getPrice();
		}
		
		System.out.println("Sales Taxes: " + ServiceUtil.roundPrice(totalTaxValue));
		System.out.println("Total: " + ServiceUtil.roundPrice(totalValue));
		
	}
	
}
