package com.enoca.project.Business;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.enoca.project.DataAccess.IProductDal;
import com.enoca.project.Entities.Product;

@Service
public class ProductManager implements IProductService {
	private IProductDal productDal;

	@Autowired
	public ProductManager(IProductDal productDal) {
		this.productDal = productDal;
	}

	@Override
	@Transactional
	public List<Product> getAll() {
		return productDal.getAll();
	}

	@Override
	@Transactional
	public void create(Product product) {
	     this.productDal.create(product);

	}

	@Override
	@Transactional
	public void update(Product product,int id) {
		this.productDal.update(product,id);


	}

	@Override
	@Transactional
	public void deleteById(int id) {
		this.productDal.deleteById(id);

	}

	@Override
	@Transactional
	public Product getById(int id) {
		return this.productDal.getById(id);
	}

}
