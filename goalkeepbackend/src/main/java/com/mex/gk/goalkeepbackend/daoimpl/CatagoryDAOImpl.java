package com.mex.gk.goalkeepbackend.daoimpl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.mex.gk.goalkeepbackend.dao.CategoryDAO;
import com.mex.gk.goalkeepbackend.dto.Category;

@Repository("categoryDAO")
public class CatagoryDAOImpl implements CategoryDAO {

	private static List<Category> categories = new ArrayList();

	static {
		// adding category
		Category category = new Category();
		category.setId(1);
		category.setName("Television");
		category.setDescription("this is a description for television");
		category.setImageURL("CAT_1.png");
		categories.add(category);

		category = new Category();
		category.setId(2);
		category.setName("Mobile");
		category.setDescription("this is a description for mobile");
		category.setImageURL("CAT_2.png");
		categories.add(category);

		category = new Category();
		category.setId(3);
		category.setName("Laptop");
		category.setDescription("this is a description for laptop");
		category.setImageURL("CAT_3.png");
		categories.add(category);

	}

	@Override
	public List<Category> list() {
		return categories;
	}

	@Override
	public Category get(int id) {		
		//return null;
		Category retCat = null;
		for (Category category : categories) {
			if (category.getId() == id) {
				retCat = category;
			}
		}
		
		return retCat;
	}

}
