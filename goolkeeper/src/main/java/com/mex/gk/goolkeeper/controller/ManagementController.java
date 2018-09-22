package com.mex.gk.goolkeeper.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.mex.gk.goalkeepbackend.dao.CategoryDAO;
import com.mex.gk.goalkeepbackend.dao.ProductDAO;
import com.mex.gk.goalkeepbackend.dto.Category;
import com.mex.gk.goalkeepbackend.dto.Product;
import com.mex.gk.goalkeepbackend.validator.ProductValidator;
import com.mex.gk.goolkeeper.util.FileUploadUtility;

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
	
	
	@RequestMapping(value="/product/{id}/activation", method=RequestMethod.POST)
	@ResponseBody
	public String handleProductActivation(@PathVariable int id){
		
		Product product = productDAO.get(id);
		boolean isActive =  product.isActive();
		product.setActive(!product.isActive());
		productDAO.update(product);		
		return (isActive)? "You have sucessfully deactivated product with id "+product.getId()
			: "You have sucessfully activated product with id "+product.getId() ;
	}
	
	@RequestMapping(value="/{id}/product", method=RequestMethod.GET)
	public ModelAndView showEditProducts(@PathVariable(name="id", required=true)int id ){
		
		ModelAndView mv = new ModelAndView("page-shop");
		Product nProduct = productDAO.get(id);		
		mv.addObject("product", nProduct);
		
		mv.addObject("userClickManageProducts", true);
		mv.addObject("title", "Manage Products");
		
		return mv;
	}
	
	@RequestMapping(value="/products", method=RequestMethod.GET)
	public ModelAndView showManageProducts(@RequestParam(name="operation", required=false)String operation ){
		
		ModelAndView mv = new ModelAndView("page-shop");

		//Init new Product
		Product nProduct = new Product();
		nProduct.setSupplierId(1);
		nProduct.setActive(true);
		mv.addObject("product", nProduct);
		
		mv.addObject("userClickManageProducts", true);
		mv.addObject("title", "Manage Products");
		
		if (operation != null) {
			if (operation.equals("product")) {
				mv.addObject("message", "Product Submited Successfully");
			}else if (operation.equals("category")) {
				mv.addObject("message", "Category Submited Successfully");
			}			
		}
		return mv;
	}	

	@RequestMapping(value="/products", method=RequestMethod.POST)
	public String handleProductSubmition(@Valid @ModelAttribute("product") Product mProduct
			, BindingResult result, Model model, HttpServletRequest request){

		//Product Validator
		if (mProduct.getId()==0) {
			new ProductValidator().validate(mProduct, result);
		}else {
			if (!mProduct.getFile().getOriginalFilename().equals("")) {
				new ProductValidator().validate(mProduct, result);
			}
		}
		
		//Check if there any errors
		if (result.hasErrors()) {
			model.addAttribute("userClickManageProducts", true);
			model.addAttribute("title", "Manage Products");
			model.addAttribute("message", "Validation failed for Product Submission!");
			
			return "page-shop";
		}
		
		System.out.println(mProduct.toString());
		if (mProduct.getId()==0) {
			productDAO.add(mProduct);
		}else{
			productDAO.update(mProduct);			
		}
		
		
		if (!mProduct.getFile().getOriginalFilename().equals("")) {
			FileUploadUtility.uploadFile(request, mProduct.getFile(), mProduct.getCode() );
		}
		
		return "redirect:/manage/products?operation=product";
	}
	
	
	@ModelAttribute("categories")
	public List<Category> getCategories(){
				
		return categoryDAO.list();
	}
	
	@ModelAttribute("category")
	public Category getCategory(){
				
		return new Category();
	}
	
	@RequestMapping(value="/category", method=RequestMethod.POST)
	public String handleCategorySubmssion(@ModelAttribute Category category){
		
		categoryDAO.add(category);
		return "redirect:/manage/products/?operation=category";
		
	}
}
