package com.mex.gk.goolkeeper.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.mex.gk.goalkeepbackend.dao.CategoryDAO;
import com.mex.gk.goalkeepbackend.dao.ProductDAO;
import com.mex.gk.goalkeepbackend.dto.Category;
import com.mex.gk.goalkeepbackend.dto.Product;

@Controller
@RequestMapping("/manage")
public class ManagementController {

	/*public ManagementController() {
		// TODO Auto-generated constructor stub
	}*/
	@Autowired
	private CategoryDAO categoryDAO;
	
	@Autowired
	private ProductDAO productDAO;
	
	@RequestMapping(value="/products", method=RequestMethod.GET)
	public ModelAndView showManageProducts(@RequestParam(name="operation", required=false)String operation ){
		
		ModelAndView mv = new ModelAndView("page-shop");
		mv.addObject("userClickManageProducts", true);
		mv.addObject("title", "Manage Products");
		Product nProduct = new Product();
		nProduct.setSupplierId(1);
		nProduct.setActive(true);
		mv.addObject("product", nProduct);
		
		
		if (operation != null) {
			mv.addObject("message", "Product Submited Successfully");
		}
		return mv;
	}
	
	

	@RequestMapping(value="/products", method=RequestMethod.POST)
	public String handleProductSubmition(@ModelAttribute("product") Product mProduct){
		
		productDAO.add(mProduct);
		
		return "redirect:/manage/products?operation=product";
	}
	
	
	@ModelAttribute("categories")
	public List<Category> getCategories(){
		
		
		return categoryDAO.list();
	}
	
}
