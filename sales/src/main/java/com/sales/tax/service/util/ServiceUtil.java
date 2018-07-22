package com.sales.tax.service.util;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * @author caio.rodrigo.santos
 * Class used to auxiliary the Service layer.
 */
public final class ServiceUtil {

	
	/**
	 * Method used to round the price based on its tax rate.
	 * 
	 * @param price
	 * @param taxRate
	 * @return
	 */
	public static Double round(Double price, Double taxRate) {
		return roundPrice(price + getRoundedTaxValue(price, taxRate));
	}
	
	/**
	 * Method used to round by two decimal places.
	 * @param amount
	 * @return
	 */
	public static double roundPrice(double amount) {
		return new BigDecimal(amount).setScale(2, RoundingMode.HALF_UP)
				.doubleValue();
	}
	
	/**
	 * Method used to round the price nearest 0.05 and return the taxed amount.
	 * @param price
	 * @param taxRate
	 * @return
	 */
	public static Double getRoundedTaxValue(Double price, Double taxRate) {
		Double taxValue = price * taxRate;
		Double roundedTaxValue = new BigDecimal(Math.ceil(taxValue * 20)/20).setScale(2,RoundingMode.HALF_UP).doubleValue();
		return roundedTaxValue;
		
	}
	
}
