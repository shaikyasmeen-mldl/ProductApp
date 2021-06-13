package com.rakuten.training.dal;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.rakuten.training.domain.Review;

@Repository
@Transactional
public class ReviewDAOJpaImpl implements ReviewDAO {
	
	@Autowired
	EntityManager em;

	@Override
	public Review save(Review r) {
		em.persist(r);
		return r;
	}

	@Override
	public Review findById(int id) {
		return em.find(Review.class, id);
	}

	@Override
	public void deleteById(int id) {
		Review r = em.find(Review.class, id);
		em.remove(r);
	}
	@Override
	public List<Review> findByPid(int productId) {
		Query q = em.createQuery("select r from Review r where r.product.id=:x");
		q.setParameter("x", productId);
		return q.getResultList();
	}

	@Override
	public List<Review> findByRatingGreaterThan(int rating) {
		Query q = em.createQuery("select r from Review r where r.rating>=:x");
		q.setParameter("x", rating);
		return q.getResultList();
	}
	
	

}