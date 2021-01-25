package com.enoca.project.Business;

import java.util.List;

import com.enoca.project.Entities.Product;


public interface IProductService {
	List<Product> getAll();
	void create(Product product);
	void update(Product product,int id);
	void deleteById(int id);
	Product getById(int id);

}
