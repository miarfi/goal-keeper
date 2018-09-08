package com.mex.gk.goolkeeper.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.mex.gk.goalkeepbackend.dao.CategoryDAO;
import com.mex.gk.goalkeepbackend.dto.Category;

@Controller
public class PageController {

	@Autowired
	private CategoryDAO categoryDAO;
	
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
				
		ModelAndView mv = new ModelAndView("page-shop");
		mv.addObject("title",category.getName());
		mv.addObject("userClickCategoryProducts", true);
		
		mv.addObject("categories", categoryDAO.list());
		mv.addObject("category", category);
		return mv;
	}
}
