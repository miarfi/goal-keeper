package com.mex.gk.goalkeepbackend.daoimpl;

import java.util.ArrayList;
import java.util.List;


import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.mex.gk.goalkeepbackend.dao.CategoryDAO;
import com.mex.gk.goalkeepbackend.dto.Category;

@Repository("categoryDAO")
public class CatagoryDAOImpl implements CategoryDAO {

	@Autowired
	private SessionFactory sessionFactory;
	
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
	@Transactional
	public List<Category> list() {
		//return categories;
		
		String selectCategory = "FROM Category WHERE active = :active";
		
		Query query = sessionFactory.getCurrentSession().createQuery(selectCategory);
		
		query.setParameter("active", true);
		
		return query.getResultList();
	}

	@Override
	@Transactional
	public Category get(int id) {		
		
		/*Category retCat = null;
		for (Category category : categories) {
			if (category.getId() == id) {
				retCat = category;
			}
		}
		
		return retCat;*/
		return sessionFactory.getCurrentSession().get(Category.class, Integer.valueOf(id));
	}

	@Override
	@Transactional
	public boolean add(Category category) {
		
		try {
			sessionFactory.getCurrentSession().persist(category);
			
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		
	}

	@Override
	@Transactional
	public boolean update(Category category) {
		try {
			sessionFactory.getCurrentSession().update(category);
			
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	@Transactional
	public boolean delete(Category category) {
		try {
			sessionFactory.getCurrentSession().delete(category);
			
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

}
