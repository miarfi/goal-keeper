package com.mex.gk.goalkeepbackend.test;

import static org.junit.Assert.assertEquals;

import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.mex.gk.goalkeepbackend.dao.UserDAO;
import com.mex.gk.goalkeepbackend.dto.Address;
import com.mex.gk.goalkeepbackend.dto.Cart;
import com.mex.gk.goalkeepbackend.dto.User;

public class UserTestCase {

	private static AnnotationConfigApplicationContext context;
	private static UserDAO userDAO;
	private User user = null;
	private Cart cart = null;
	private Address address = null;
	
	
	@BeforeClass
	public static void init() {
		context = new AnnotationConfigApplicationContext();
		context.scan("com.mex.gk.goalkeepbackend");
		context.refresh();
		
		userDAO = (UserDAO) context.getBean("userDAO");
	}
	
/*
	@Test
	public void testAddUser() {
		
		user = new User() ;
		user.setFirstName("Pedro");
		user.setLastName("Perez");
		user.setEmail("pp@gmail.com");
		user.setContactNumber("1234512345");
		user.setRole("CUSTOMER");
		user.setEnabled(true);
		user.setPassword("12345");
		
		
		address = new Address();
		address.setAddressLineOne("101/B Jadoo Society, Krissh Nagar");
		address.setAddressLineTwo("Near Kaabil Store");
		address.setCity("Mumbai");
		address.setState("Maharashtra");
		address.setCountry("India");
		address.setPostalCode("400001");
		address.setBilling(true);
		//address.setUserId(user.getId());
		cart = new Cart();
		
		// linked the address with the user
		
		//cart.setUser(user);
		
		// linked the cart with the user
		cart.setUser(user);
		// link the user with the cart
		user.setCart(cart);
		
		// add the user
		assertEquals("Failed to add the user!", true, userDAO.add(user));	
		address.setUserId(user.getId());
		// add the address
		assertEquals("Failed to add the billing address!", true, userDAO.addAddress(address));

				
		// add the shipping address
		address = new Address();
		address.setAddressLineOne("201/B Jadoo Society, Kishan Kanhaiya Nagar");
		address.setAddressLineTwo("Near Kudrat Store");
		address.setCity("Mumbai");
		address.setState("Maharashtra");
		address.setCountry("India");
		address.setPostalCode("400001");
		address.setUserId(user.getId());
		//address.setUser(user);
		assertEquals("Failed to add the shipping address!", true, userDAO.addAddress(address));
		
	}
	
	
*/
	// working for uni-directional

	@Test
	public void testAddAddress() {
		user = userDAO.getByEmail("pp@gmail.com");
		/*
		user = new User() ;
		user.setFirstName("Carmen");
		user.setLastName("Perez");
		user.setEmail("pp@gmail.com");
		user.setContactNumber("1234512345");
		user.setRole("CUSTOMER");
		user.setEnabled(true);
		user.setPassword("12345");
		
		assertEquals("Failed to add user!", true, userDAO.add(user));	
		
		address = new Address();
		address.setAddressLineOne("250 main ave");
		address.setAddressLineTwo("Near Store");
		address.setCity("Coyoacan");
		address.setState("DF");
		address.setCountry("Mexico");
		address.setPostalCode("400001");
		address.setBilling(true);
				
		address.setUser(user);
		// add the address
		assertEquals("Failed to add the address!", true, userDAO.addAddress(address));	
		*/

		address = new Address();
		address.setAddressLineOne("Ingenieros Militares #40000");
		address.setAddressLineTwo("Near Store");
		address.setCity("GMA");
		address.setState("DF");
		address.setCountry("Mexico");
		address.setPostalCode("400003");
		address.setShipping(true);
				
		address.setUser(user);
		// add the address
		assertEquals("Failed to add the address!", true, userDAO.addAddress(address));
	}
	
	/*
	@Test
	public void testUpdateCart() {
		user = userDAO.get(1);
		cart = user.getCart();
		cart.setGrandTotal(10000);
		cart.setCartLines(1);
		assertEquals("Failed to update the cart!", true, userDAO.updateCart(cart));			
	} 
*/

	

	
}
