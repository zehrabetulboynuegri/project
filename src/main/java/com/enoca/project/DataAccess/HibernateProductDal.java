package com.enoca.project.DataAccess;

import java.util.List;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.enoca.project.Entities.Product;

@Repository
public class HibernateProductDal implements IProductDal {
	private EntityManager entityManager;

	@Autowired
	public HibernateProductDal(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	@Override
	@Transactional
	public List<Product> getAll() {
		Session session = entityManager.unwrap(Session.class);
		List<Product> products = session.createQuery("from Product", Product.class).getResultList();
		return products;
	}

	@Override
	public void create(Product product) {
		Session session = entityManager.unwrap(Session.class);
		session.save(product);

	}

	@Override
	public void update(Product product, int id) {
		Session session = entityManager.unwrap(Session.class);
		Product productToUpdate = session.get(Product.class, id);
		productToUpdate.setProductId(product.getProductId());
		productToUpdate.setProductName(product.getProductName());
		productToUpdate.setProductDescription(product.getProductDescription());
		productToUpdate.setProductPrice(product.getProductPrice());
		session.saveOrUpdate(productToUpdate);
	}

	@Override
	public void deleteById(int id) {
		Session session = entityManager.unwrap(Session.class);
		Product productToDelete = session.get(Product.class, id);
		session.delete(productToDelete);
	}

	@Override
	public Product getById(int id) {
		Session session = entityManager.unwrap(Session.class);
		Product product = session.get(Product.class, id);
		return product;
	}

}
