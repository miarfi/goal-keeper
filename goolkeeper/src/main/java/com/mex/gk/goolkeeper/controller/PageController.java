package com.mex.gk.goolkeeper.controller;

//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.mex.gk.goalkeepbackend.dao.CategoryDAO;
import com.mex.gk.goalkeepbackend.dao.ProductDAO;
import com.mex.gk.goalkeepbackend.dto.Category;
import com.mex.gk.goalkeepbackend.dto.Product;
import com.mex.gk.goolkeeper.exception.ProductNotFoundException;

@Controller
public class PageController {

	//private static final Logger logger = LoggerFactory.getLogger(PageController.class);
	@Autowired
	private CategoryDAO categoryDAO;
	
	@Autowired
	private ProductDAO productDAO;
	
	@RequestMapping(value = {"/","/index"})
	public ModelAndView index(){
		
		ModelAndView mv = new ModelAndView("page");
		mv.addObject("greeting", "Welcome to Spring MVC");
		return mv;
	}
	
	@RequestMapping(value = {"/test"})
	public ModelAndView test(@RequestParam(value="greeting", required=false) String greeting){
		
		if (greeting == null) {
			greeting = "Hello there";
		} 
		
		ModelAndView mv = new ModelAndView("page-shop");
		//mv.addObject("greeting", greeting);

		return mv;
	}
	
	@RequestMapping(value = {"/testp/{greeting}"})
	public ModelAndView testp(@PathVariable("greeting")String greeting){
		if (greeting == null) {
			greeting = "Hello there";
		} 
			
		ModelAndView mv = new ModelAndView("page");
		mv.addObject("greeting", greeting);
		return mv;
	}
	
	@RequestMapping(value = {"/home"})
	public ModelAndView home(){
		//logger.info("Inside PageController home method");
		//logger.debug("Inside PageController home method");
		ModelAndView mv = new ModelAndView("page-shop");
		mv.addObject("title", "Home");
		mv.addObject("userClickHome", true);
		
		mv.addObject("categories", categoryDAO.list());
		return mv;
	}	

	@RequestMapping(value = {"/about"})
	public ModelAndView about(){
		
		ModelAndView mv = new ModelAndView("page-shop");
		mv.addObject("title", "About");
		mv.addObject("userClickAbout", true);
		return mv;
	}	

	@RequestMapping(value = {"/contact"})
	public ModelAndView contact(){
		
		ModelAndView mv = new ModelAndView("page-shop");
		mv.addObject("title", "Contact");
		mv.addObject("userClickContact", true);
		return mv;
	}	
	
	/*
	 * 
	 * */
	@RequestMapping(value = {"/show/all/products"})
	public ModelAndView showAllProducts(){
		
		ModelAndView mv = new ModelAndView("page-shop");
		mv.addObject("title", "All Products");
		mv.addObject("userClickAllProducts", true);
		
		mv.addObject("categories", categoryDAO.list());
		return mv;
	}
	
	@RequestMapping(value = {"/show/category/{id}/products"})
	public ModelAndView showCategoryProducts(@PathVariable("id")int id){
		
		//categoryDAO to fetch a single category
		Category category = null;
		category = categoryDAO.get(id);
		//System.out.println("category: "+category.getName());
				
		ModelAndView mv = new ModelAndView("page-shop");
		mv.addObject("title",category.getName());
		mv.addObject("userClickCategoryProducts", true);
		
		mv.addObject("categories", categoryDAO.list());
		mv.addObject("category", category);
		return mv;
	}
	
	@RequestMapping(value = {"/show/{id}/product"})
	public ModelAndView showSimgleProduct(@PathVariable("id")int id) throws ProductNotFoundException{
		
		//categoryDAO to fetch a single category
		Product product = null;
		product = productDAO.get(id);
		
		if (product == null) {
			throw new ProductNotFoundException();
			
		}
		
		product.setViews(product.getViews()+1);
		productDAO.update(product);	
				
		ModelAndView mv = new ModelAndView("page-shop");
		mv.addObject("title",product.getName());
		mv.addObject("userClickShowSingleProduct", true);		
		mv.addObject("product", product);
		return mv;
	}	
}
