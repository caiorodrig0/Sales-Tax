package com.sales.tax;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;


/**
 * @author caio.rodrigo.santos
 *
 */

@SpringBootApplication
@ComponentScan({"com.sales.tax.repository", "com.sales.tax.service", "com.sales.tax.controller"})
public class SpringBootInit 
{
    public static void main( String[] args )
    {
    	 //Initializing Spring Boot
    	 SpringApplication.run(SpringBootInit.class, args);
    }
}
