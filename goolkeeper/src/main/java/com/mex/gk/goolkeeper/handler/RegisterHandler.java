package com.mex.gk.goolkeeper.handler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.binding.message.MessageContext;
import org.springframework.stereotype.Component;

import com.mex.gk.goalkeepbackend.dao.UserDAO;
import com.mex.gk.goalkeepbackend.dto.Address;
import com.mex.gk.goalkeepbackend.dto.Cart;
import com.mex.gk.goalkeepbackend.dto.User;
import com.mex.gk.goolkeeper.model.RegisterModel;

@Component
public class RegisterHandler {
	
	@Autowired
	UserDAO userDAO;
	
	public RegisterModel init() {
		return new RegisterModel();
	}	
	
	public void addUser(RegisterModel registerModel,User user){
		registerModel.setUser(user);
	}
	
	public void addBilling(RegisterModel registerModel, Address billing){
		registerModel.setBilling(billing);
	}	
	
	public String saveAll(RegisterModel model){
		String transitionValue = "success";
		
		User user = model.getUser();
		
		if (user.getRole().equals("USER")) {
			Cart cart = new Cart();
			cart.setUser(user);
			user.setCart(cart);
		}
		
		userDAO.add(user);
		
		Address billing = model.getBilling();
		billing.setUser(user);
		billing.setBilling(true);
		
		userDAO.addAddress(billing);
		
		return transitionValue;
	}
	
	public String validateUser(User user, MessageContext error) {
		
		String transitionValue = "success";
		
		if (!(user.getPassword().equals(user.getConfirmPassword()))) {
			//error.addMessage(new MessageBuilder().error().source(""));
		}
		
		
		return transitionValue;
		
	}
}
