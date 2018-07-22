package com.sales.tax.service.validator;

/**
 * 
 * We are using this class to keep all methods that can help us in development.
 * @author caio.rodrigo.santos
 *
 */
public final class ValidatorUtil {

	/**
	 * Check if an object is null.
	 * @param obj
	 * @return
	 */
	public static Boolean isNull(Object obj) {
		if(obj == null) return true;
		return false;
	}
	
}
