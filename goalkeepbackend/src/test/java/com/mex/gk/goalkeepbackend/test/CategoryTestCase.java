package com.mex.gk.goalkeepbackend.test;

import static org.junit.Assert.assertEquals;

import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.mex.gk.goalkeepbackend.dao.CategoryDAO;
import com.mex.gk.goalkeepbackend.dto.Category;

public class CategoryTestCase {

	
	private static AnnotationConfigApplicationContext context;

	private static CategoryDAO categoryDAO;
	
	private Category category;
	
	@BeforeClass
	public static void init(){
		
		context = new AnnotationConfigApplicationContext();
		context.scan("com.mex.gk.goalkeepbackend");
		context.refresh();		
		categoryDAO = (CategoryDAO) context.getBean("categoryDAO");		
	}
	
	/*
	@Test
	public void testAddCategory(){
		
		category = new Category();
		category.setName("Modem");
		category.setDescription("this is a description for modem");
		category.setImageURL("CAT_4.png");
		
		assertEquals("Succesfully added a category", true, categoryDAO.add(category));
	}	
	*/
	/*
	@Test
	public void testGetCategory(){
		category = categoryDAO.get(2);
		assertEquals("Succesfully fetched a category", "Television", category.getName());
	}
	*/
	/*
	@Test
	public void testUpdCategory(){
		
		category = categoryDAO.get(2);
		category.setName("TV");
		
		assertEquals("Succesfully added a category", true, categoryDAO.update(category));
	}
	*/
	/*
	@Test
	public void testDelCategory(){
		
		category = categoryDAO.get(33);		
		
		assertEquals("Succesfully added a category", true, categoryDAO.delete(category));
	}
	
	*/
	@Test
	public void testListCategories(){
		
		assertEquals("Succesfully added a category", 3, categoryDAO.list().size());
	}
}
