package datapagedquery.service;

import org.apache.log4j.Logger;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.support.GenericXmlApplicationContext;
import org.springframework.data.domain.Page;

import datapagedquery.domain.Customer;

public class TestCustomerService {
	static Logger log = Logger.getLogger( 
			 TestCustomerService.class.getName()); 
	static CustomerService service;

	@BeforeClass
	public static void init() {
		GenericXmlApplicationContext ctx = new GenericXmlApplicationContext();
		ctx.load("classpath:applicationContext.xml");
		ctx.refresh();
		
		service = ctx.getBean("customerService", CustomerService.class);
	}
	
	@Test
	public void testFindAllPaged() {
		Page<Customer> page = service.findAll(1, 10);
		
		for (Customer c : page.getContent()) {
			log.info(c);
		}		
	}
	
	@Test
	public void testFindByPatternPaged() {
		Page<Customer> page = service.findByNamePatternPaged("customer", 1, 10);
		
		for (Customer c : page.getContent()) {
			log.info(c);
		}		
	}
	

}
