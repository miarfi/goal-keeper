package com.mex.gk.goalkeepbackend.dao;

import java.util.List;

import com.mex.gk.goalkeepbackend.dto.Address;
import com.mex.gk.goalkeepbackend.dto.Cart;
import com.mex.gk.goalkeepbackend.dto.User;

public interface UserDAO {

	// user related operation
	User getByEmail(String email);
	User get(int id);

	boolean add(User user);
	
	// adding and updating a new address
	Address getAddress(int addressId);
	boolean addAddress(Address address);
	boolean updateAddress(Address address);
	//Address getBillingAddress(int userId);
	//List<Address> listShippingAddresses(int userId);
	Address getBillingAddress(User user);
	List<Address> listShippingAddresses(User user);
	
	boolean updateCart(Cart cart);
	
	
}
