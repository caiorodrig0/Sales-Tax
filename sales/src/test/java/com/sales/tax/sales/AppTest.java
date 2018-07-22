package com.sales.tax.sales;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import junit.framework.Assert;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.sales.tax.controller.SalesController;
import com.sales.tax.domain.DTO.ProductDTO;
import com.sales.tax.domain.enumerations.MessageTypeEnum;
import com.sales.tax.domain.enumerations.ProductTypeEnum;
import com.sales.tax.domain.model.Product;
import com.sales.tax.sales.util.SalesTestsUtil;


@SpringBootTest
@RunWith(SpringRunner.class)
public class AppTest 
{
	@Autowired
	SalesController salesController;
	
	/**
	 * @throws Exception
	 * 
	 * First method to be called. In that context, we are using to populate our database with valid values.
	 */
	@Before
	public void setUp() throws Exception {
		salesController.deleteAll();
		//Input 1:
		assertEquals(salesController.addProduct("book", ProductTypeEnum.BOOK.getType(), false, 12.49).getMessage().getId(), MessageTypeEnum.SUCCESS.getType());
		assertEquals(salesController.addProduct("music CD", ProductTypeEnum.OTHER.getType(), false, 14.99).getMessage().getId(), MessageTypeEnum.SUCCESS.getType());
		assertEquals(salesController.addProduct("chocolate bar", ProductTypeEnum.FOOD.getType(), false, 0.85).getMessage().getId(), MessageTypeEnum.SUCCESS.getType());
		
		//Input 2:
		assertEquals(salesController.addProduct("imported box of chocolates", ProductTypeEnum.FOOD.getType(), true, 10.00).getMessage().getId(), MessageTypeEnum.SUCCESS.getType());
		assertEquals(salesController.addProduct("imported bottle of perfume", ProductTypeEnum.OTHER.getType(), true, 47.50).getMessage().getId(), MessageTypeEnum.SUCCESS.getType());
		
		//Input 3:
		assertEquals(salesController.addProduct("imported bottle of perfume", ProductTypeEnum.OTHER.getType(), true, 27.99).getMessage().getId(), MessageTypeEnum.SUCCESS.getType());
		assertEquals(salesController.addProduct("bottle of perfume", ProductTypeEnum.OTHER.getType(), false, 18.99).getMessage().getId(), MessageTypeEnum.SUCCESS.getType());
		assertEquals(salesController.addProduct("packet of headache pills", ProductTypeEnum.MEDICAL.getType(), false, 9.75).getMessage().getId(), MessageTypeEnum.SUCCESS.getType());
		assertEquals(salesController.addProduct("imported box of chocolates", ProductTypeEnum.FOOD.getType(), true, 11.25).getMessage().getId(), MessageTypeEnum.SUCCESS.getType());
	}
	
	
	
	
    /**
     *  Unit test to validate the Input 1.
     */
    @Test
    public void inputOne()
    {
    	List<ProductDTO> products = new ArrayList<ProductDTO>();
    	Product product = new Product();
    	String[] entries = {"1 book at 12.49", "1 music CD at 14.99", "1 chocolate bar at 0.85"}; //Creates the array with the input values just like mentioned in the document
    	Double[] expectedResultsForPrices = {12.49, 16.49, 0.85}; //Creates the array with the expected values to this test
    	
    	
    	System.out.println("OUTPUT 1:");
    	for (int i = 0; i < entries.length; i++) {
			
		    
    		product = SalesTestsUtil.parser(entries[i]); //Calls the parser to split the values into a Product.    	
    		
        	assertNotNull(product.getCompositeKey());  //Validation if it was everything ok with the parser.
        	
        	products.add(salesController.purchase(product.getCompositeKey().getItem(), 
        			Integer.valueOf(entries[i].split(" ")[0]), product.getCompositeKey().getIsImported(), product.getCompositeKey().getPrice())); //Populates the list with products purchased.

        	assertNotNull(products.get(i).getProduct()); //Validation to prevent a Nullpointer in the next line
        	assertEquals(expectedResultsForPrices[i], products.get(i).getProduct().getCompositeKey().getPrice()); //Validation if the price of the product is as expected.
    	}	
    
    	
    	SalesTestsUtil.printSalesTax(products); //Prints the result as mentioned in the document.
    	
    }
    
    /**
     *  Unit test to validate the Input 2.
     */
    @Test
    public void inputTwo()
    {
    	List<ProductDTO> products = new ArrayList<ProductDTO>();
    	Product product = new Product();
    	String[] entries = {"1 imported box of chocolates at 10.00", "1 imported bottle of perfume at 47.50"}; //Creates the array with the input values just like mentioned in the document
    	Double[] expectedResultsForPrices = {10.50, 54.65}; //Creates the array with the expected values to this test
    	
    	System.out.println("\nOUTPUT 2:");
    	for (int i = 0; i < entries.length; i++) {
    		
    		product = SalesTestsUtil.parser(entries[i]); //Calls the parser to split the values into a Product. 
    		
    		assertNotNull(product.getCompositeKey()); //Validation if it was everything ok with the parser.
    		
    		products.add(salesController.purchase(product.getCompositeKey().getItem(), 
        			Integer.valueOf(entries[i].split(" ")[0]), product.getCompositeKey().getIsImported(), product.getCompositeKey().getPrice())); //Populates the list with products purchased.
    		
    		assertNotNull(products.get(i).getProduct()); //Validation to prevent a Nullpointer in the next line
        	assertEquals(expectedResultsForPrices[i], products.get(i).getProduct().getCompositeKey().getPrice()); //Validation if the price of the product is as expected.

		}
    
    	SalesTestsUtil.printSalesTax(products); //Prints the result as mentioned in the document.
    }
    
    /**
     *  Unit test to validate the Input 3.
     */
    @Test
    public void inputThree()
    {
    	List<ProductDTO> products = new ArrayList<ProductDTO>();
    	Product product = new Product();
    	String[] entries = {"1 imported bottle of perfume at 27.99", "1 bottle of perfume at 18.99", 
    			"1 packet of headache pills at 9.75", "1 imported box of chocolates at 11.25"}; //Creates the array with the input values just like mentioned in the document
    	Double[] expectedResultsForPrices = {32.19, 20.89, 9.75, 11.85}; //Creates the array with the expected values to this test
    	
    	
    	System.out.println("\nOUTPUT 3:");
    	for (int i = 0; i < entries.length; i++) {
    		
    		product = SalesTestsUtil.parser(entries[i]);  //Calls the parser to split the values into a Product.
    		
    		assertNotNull(product.getCompositeKey()); //Validation if it was everything ok with the parser.
    		
    		products.add(salesController.purchase(product.getCompositeKey().getItem(), Integer.valueOf(entries[i].split(" ")[0]), 
        			product.getCompositeKey().getIsImported(), product.getCompositeKey().getPrice())); //Populates the list with products purchased.
    		
    		assertNotNull(products.get(i).getProduct()); //Validation to prevent a Nullpointer in the next line
        	assertEquals(expectedResultsForPrices[i], products.get(i).getProduct().getCompositeKey().getPrice()); //Validation if the price of the product is as expected.
    	}
  
    	SalesTestsUtil.printSalesTax(products); //Prints the result as mentioned in the document.
    }
    
    /**
     * @throws Exception
     * Last method to be called. We are using it to clear the database after unit tests.
     */
    @After
    public void tearDown() throws Exception {
    	assertEquals(salesController.deleteAll().getMessage().getId(), MessageTypeEnum.SUCCESS.getType());
    	
    }
    
    
}
