package com.mex.gk.goolkeeper.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class PageController {

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
}
