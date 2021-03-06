package com.mex.gk.goolkeeper.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mex.gk.goalkeepbackend.dao.ProductDAO;
import com.mex.gk.goalkeepbackend.dto.Product;

@Controller
@RequestMapping("/json/data")
public class JsonDataController {

	@Autowired
	private ProductDAO productDAO;
	
	@RequestMapping("/all/products")
	@ResponseBody
	public List<Product> getAllProducts(){
		
		return productDAO.listActiveProducts();
	}

	@RequestMapping("/admin/all/products")
	@ResponseBody
	public List<Product> getAdminProducts(){
		
		return productDAO.list();
	}
	
	@RequestMapping("category/{id}/products")
	@ResponseBody
	public List<Product> getProductsByCategory(@PathVariable int id){
		
		return productDAO.listActiveProductsByCategory(id);
	}

}
