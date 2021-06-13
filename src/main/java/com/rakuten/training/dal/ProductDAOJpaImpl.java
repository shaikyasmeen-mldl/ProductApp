package com.rakuten.training.dal;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.rakuten.training.domain.Product;

@Primary
@Repository
@Transactional
public class ProductDAOJpaImpl implements ProductDAO{
	
	@Autowired
	EntityManager em;

	@Override
	public Product save(Product toBeSaved) {
		//toBeSaved----> New/Transient
		em.persist(toBeSaved);
		//toBeSaved----> Managed/Persistent
		return toBeSaved;
		
	}

	@Override
	public List<Product> findAll() {
		Query q = em.createQuery("select p from Product as p");
		return q.getResultList();
	}

	@Override
	public Product findById(int id) {
		return em.find(Product.class, id);
		
	}

	@Override
	public void deleteById(int id) {
		Product p = em.find(Product.class, id); //em
		if(p!=null)
		{
			em.remove(p);
		}
	
	}
	@Override
	public List<Product> findByName(String name) {
		Query q = em.createQuery("select p from Product p where p.name=:n");
		q.setParameter("n", name);
		return q.getResultList();
	}
	
	@Override
	public List<Product> findByNameLike(String name) {
		Query q = em.createQuery("select p from Product p where p.name like :n");
		q.setParameter("n", "%"+name+"%");
		return q.getResultList();
	}
	
	@Override
	public List<Product> findByPriceLessThan(float price) {
		Query q = em.createQuery("select p from Product p where p.price<:n");
		q.setParameter("n", price);
		return q.getResultList();
	}
}



