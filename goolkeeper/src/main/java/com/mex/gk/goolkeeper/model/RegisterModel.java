package com.mex.gk.goolkeeper.model;

import java.io.Serializable;

import com.mex.gk.goalkeepbackend.dto.Address;
import com.mex.gk.goalkeepbackend.dto.User;

public class RegisterModel implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private User user;
	private Address billing;
	
	
	public Address getBilling() {
		return billing;
	}
	public void setBilling(Address billing) {
		this.billing = billing;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}

	
}
